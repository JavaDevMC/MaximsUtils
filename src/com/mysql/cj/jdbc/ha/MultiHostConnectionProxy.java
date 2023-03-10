package com.mysql.cj.jdbc.ha;

import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.util.Util;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

public abstract class MultiHostConnectionProxy implements InvocationHandler {
  private static final String METHOD_GET_MULTI_HOST_SAFE_PROXY = "getMultiHostSafeProxy";
  
  private static final String METHOD_EQUALS = "equals";
  
  private static final String METHOD_CLOSE = "close";
  
  private static final String METHOD_ABORT_INTERNAL = "abortInternal";
  
  private static final String METHOD_ABORT = "abort";
  
  private static final String METHOD_IS_CLOSED = "isClosed";
  
  private static final String METHOD_GET_AUTO_COMMIT = "getAutoCommit";
  
  private static final String METHOD_GET_CATALOG = "getCatalog";
  
  private static final String METHOD_GET_SCHEMA = "getSchema";
  
  private static final String METHOD_GET_DATABASE = "getDatabase";
  
  private static final String METHOD_GET_TRANSACTION_ISOLATION = "getTransactionIsolation";
  
  private static final String METHOD_GET_SESSION_MAX_ROWS = "getSessionMaxRows";
  
  List<HostInfo> hostsList;
  
  protected ConnectionUrl connectionUrl;
  
  boolean autoReconnect = false;
  
  JdbcConnection thisAsConnection = null;
  
  JdbcConnection parentProxyConnection = null;
  
  JdbcConnection topProxyConnection = null;
  
  JdbcConnection currentConnection = null;
  
  boolean isClosed = false;
  
  boolean closedExplicitly = false;
  
  String closedReason = null;
  
  protected Throwable lastExceptionDealtWith = null;
  
  class JdbcInterfaceProxy implements InvocationHandler {
    Object invokeOn = null;
    
    JdbcInterfaceProxy(Object toInvokeOn) {
      this.invokeOn = toInvokeOn;
    }
    
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if ("equals".equals(method.getName()))
        return Boolean.valueOf(args[0].equals(this)); 
      synchronized (MultiHostConnectionProxy.this) {
        Object result = null;
        try {
          result = method.invoke(this.invokeOn, args);
          result = MultiHostConnectionProxy.this.proxyIfReturnTypeIsJdbcInterface(method.getReturnType(), result);
        } catch (InvocationTargetException e) {
          MultiHostConnectionProxy.this.dealWithInvocationException(e);
        } 
        return result;
      } 
    }
  }
  
  MultiHostConnectionProxy() throws SQLException {
    this.thisAsConnection = getNewWrapperForThisAsConnection();
  }
  
  MultiHostConnectionProxy(ConnectionUrl connectionUrl) throws SQLException {
    this();
    initializeHostsSpecs(connectionUrl, connectionUrl.getHostsList());
  }
  
  int initializeHostsSpecs(ConnectionUrl connUrl, List<HostInfo> hosts) {
    this.connectionUrl = connUrl;
    Properties props = connUrl.getConnectionArgumentsAsProperties();
    this
      .autoReconnect = ("true".equalsIgnoreCase(props.getProperty(PropertyKey.autoReconnect.getKeyName())) || "true".equalsIgnoreCase(props.getProperty(PropertyKey.autoReconnectForPools.getKeyName())));
    this.hostsList = new ArrayList<>(hosts);
    int numHosts = this.hostsList.size();
    return numHosts;
  }
  
  protected JdbcConnection getProxy() {
    return (this.topProxyConnection != null) ? this.topProxyConnection : this.thisAsConnection;
  }
  
  protected JdbcConnection getParentProxy() {
    return this.parentProxyConnection;
  }
  
  protected final void setProxy(JdbcConnection proxyConn) {
    if (this.parentProxyConnection == null)
      this.parentProxyConnection = proxyConn; 
    this.topProxyConnection = proxyConn;
    propagateProxyDown(proxyConn);
  }
  
  protected void propagateProxyDown(JdbcConnection proxyConn) {
    this.currentConnection.setProxy(proxyConn);
  }
  
  JdbcConnection getNewWrapperForThisAsConnection() throws SQLException {
    return new MultiHostMySQLConnection(this);
  }
  
  Object proxyIfReturnTypeIsJdbcInterface(Class<?> returnType, Object toProxy) {
    if (toProxy != null && 
      Util.isJdbcInterface(returnType)) {
      Class<?> toProxyClass = toProxy.getClass();
      return Proxy.newProxyInstance(toProxyClass.getClassLoader(), Util.getImplementedInterfaces(toProxyClass), getNewJdbcInterfaceProxy(toProxy));
    } 
    return toProxy;
  }
  
  InvocationHandler getNewJdbcInterfaceProxy(Object toProxy) {
    return new JdbcInterfaceProxy(toProxy);
  }
  
  void dealWithInvocationException(InvocationTargetException e) throws SQLException, Throwable, InvocationTargetException {
    Throwable t = e.getTargetException();
    if (t != null) {
      if (this.lastExceptionDealtWith != t && shouldExceptionTriggerConnectionSwitch(t)) {
        invalidateCurrentConnection();
        pickNewConnection();
        this.lastExceptionDealtWith = t;
      } 
      throw t;
    } 
    throw e;
  }
  
  abstract boolean shouldExceptionTriggerConnectionSwitch(Throwable paramThrowable);
  
  abstract boolean isSourceConnection();
  
  @Deprecated
  boolean isMasterConnection() {
    return isSourceConnection();
  }
  
  synchronized void invalidateCurrentConnection() throws SQLException {
    invalidateConnection(this.currentConnection);
  }
  
  synchronized void invalidateConnection(JdbcConnection conn) throws SQLException {
    try {
      if (conn != null && !conn.isClosed())
        conn.realClose(true, !conn.getAutoCommit(), true, null); 
    } catch (SQLException sQLException) {}
  }
  
  abstract void pickNewConnection() throws SQLException;
  
  synchronized ConnectionImpl createConnectionForHost(HostInfo hostInfo) throws SQLException {
    ConnectionImpl conn = (ConnectionImpl)ConnectionImpl.getInstance(hostInfo);
    JdbcConnection topmostProxy = getProxy();
    if (topmostProxy != this.thisAsConnection)
      conn.setProxy(this.thisAsConnection); 
    conn.setProxy(topmostProxy);
    return conn;
  }
  
  void syncSessionState(JdbcConnection source, JdbcConnection target) throws SQLException {
    if (source == null || target == null)
      return; 
    RuntimeProperty<Boolean> sourceUseLocalSessionState = source.getPropertySet().getBooleanProperty(PropertyKey.useLocalSessionState);
    boolean prevUseLocalSessionState = ((Boolean)sourceUseLocalSessionState.getValue()).booleanValue();
    sourceUseLocalSessionState.setValue(Boolean.valueOf(true));
    boolean readOnly = source.isReadOnly();
    sourceUseLocalSessionState.setValue(Boolean.valueOf(prevUseLocalSessionState));
    syncSessionState(source, target, readOnly);
  }
  
  void syncSessionState(JdbcConnection source, JdbcConnection target, boolean readOnly) throws SQLException {
    if (target != null)
      target.setReadOnly(readOnly); 
    if (source == null || target == null)
      return; 
    RuntimeProperty<Boolean> sourceUseLocalSessionState = source.getPropertySet().getBooleanProperty(PropertyKey.useLocalSessionState);
    boolean prevUseLocalSessionState = ((Boolean)sourceUseLocalSessionState.getValue()).booleanValue();
    sourceUseLocalSessionState.setValue(Boolean.valueOf(true));
    target.setAutoCommit(source.getAutoCommit());
    String db = source.getDatabase();
    if (db != null && !db.isEmpty())
      target.setDatabase(db); 
    target.setTransactionIsolation(source.getTransactionIsolation());
    target.setSessionMaxRows(source.getSessionMaxRows());
    sourceUseLocalSessionState.setValue(Boolean.valueOf(prevUseLocalSessionState));
  }
  
  abstract void doClose() throws SQLException;
  
  abstract void doAbortInternal() throws SQLException;
  
  abstract void doAbort(Executor paramExecutor) throws SQLException;
  
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    String methodName = method.getName();
    if ("getMultiHostSafeProxy".equals(methodName))
      return this.thisAsConnection; 
    if ("equals".equals(methodName))
      return Boolean.valueOf(args[0].equals(this)); 
    if (method.getDeclaringClass().equals(Object.class))
      return method.invoke(this, args); 
    synchronized (this) {
      if ("close".equals(methodName)) {
        doClose();
        this.isClosed = true;
        this.closedReason = "Connection explicitly closed.";
        this.closedExplicitly = true;
        return null;
      } 
      if ("abortInternal".equals(methodName)) {
        doAbortInternal();
        this.currentConnection.abortInternal();
        this.isClosed = true;
        this.closedReason = "Connection explicitly closed.";
        return null;
      } 
      if ("abort".equals(methodName) && args.length == 1) {
        doAbort((Executor)args[0]);
        this.isClosed = true;
        this.closedReason = "Connection explicitly closed.";
        return null;
      } 
      if ("isClosed".equals(methodName))
        return Boolean.valueOf(this.isClosed); 
      try {
        return invokeMore(proxy, method, args);
      } catch (InvocationTargetException e) {
        throw (e.getCause() != null) ? e.getCause() : e;
      } catch (Exception e) {
        Class<?>[] declaredException = method.getExceptionTypes();
        for (Class<?> declEx : declaredException) {
          if (declEx.isAssignableFrom(e.getClass()))
            throw e; 
        } 
        throw new IllegalStateException(e.getMessage(), e);
      } 
    } 
  }
  
  abstract Object invokeMore(Object paramObject, Method paramMethod, Object[] paramArrayOfObject) throws Throwable;
  
  protected boolean allowedOnClosedConnection(Method method) {
    String methodName = method.getName();
    return (methodName.equals("getAutoCommit") || methodName.equals("getCatalog") || methodName.equals("getSchema") || methodName
      .equals("getDatabase") || methodName.equals("getTransactionIsolation") || methodName
      .equals("getSessionMaxRows"));
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\jdbc\ha\MultiHostConnectionProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.Query;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.util.StringUtils;
import java.sql.SQLException;
import java.util.Properties;
import java.util.function.Supplier;

public class LoadBalancedAutoCommitInterceptor implements QueryInterceptor {
  private int matchingAfterStatementCount = 0;
  
  private int matchingAfterStatementThreshold = 0;
  
  private String matchingAfterStatementRegex;
  
  private JdbcConnection conn;
  
  private LoadBalancedConnectionProxy proxy = null;
  
  private boolean countStatements = false;
  
  public void destroy() {
    this.conn = null;
    this.proxy = null;
  }
  
  public boolean executeTopLevelOnly() {
    return false;
  }
  
  public QueryInterceptor init(MysqlConnection connection, Properties props, Log log) {
    this.conn = (JdbcConnection)connection;
    String autoCommitSwapThresholdAsString = props.getProperty(PropertyKey.loadBalanceAutoCommitStatementThreshold.getKeyName(), "0");
    try {
      this.matchingAfterStatementThreshold = Integer.parseInt(autoCommitSwapThresholdAsString);
    } catch (NumberFormatException numberFormatException) {}
    String autoCommitSwapRegex = props.getProperty(PropertyKey.loadBalanceAutoCommitStatementRegex.getKeyName(), "");
    if (!"".equals(autoCommitSwapRegex))
      this.matchingAfterStatementRegex = autoCommitSwapRegex; 
    return this;
  }
  
  public <T extends com.mysql.cj.protocol.Resultset> T postProcess(Supplier<String> sql, Query interceptedQuery, T originalResultSet, ServerSession serverSession) {
    try {
      if (!this.countStatements || StringUtils.startsWithIgnoreCase(sql.get(), "SET") || StringUtils.startsWithIgnoreCase(sql.get(), "SHOW") || 
        StringUtils.startsWithIgnoreCase(sql.get(), "USE"))
        return originalResultSet; 
      if (!this.conn.getAutoCommit()) {
        this.matchingAfterStatementCount = 0;
        return originalResultSet;
      } 
      if (this.proxy == null && this.conn.isProxySet()) {
        JdbcConnection connParentProxy = this.conn.getMultiHostParentProxy();
        while (connParentProxy != null && !(connParentProxy instanceof LoadBalancedMySQLConnection))
          connParentProxy = connParentProxy.getMultiHostParentProxy(); 
        if (connParentProxy != null)
          this.proxy = ((LoadBalancedMySQLConnection)connParentProxy).getThisAsProxy(); 
      } 
      if (this.proxy == null)
        return originalResultSet; 
      if (this.matchingAfterStatementRegex == null || ((String)sql.get()).matches(this.matchingAfterStatementRegex))
        this.matchingAfterStatementCount++; 
      if (this.matchingAfterStatementCount >= this.matchingAfterStatementThreshold) {
        this.matchingAfterStatementCount = 0;
        try {
          this.proxy.pickNewConnection();
        } catch (SQLException sQLException) {}
      } 
    } catch (SQLException ex) {
      throw ExceptionFactory.createException(ex.getMessage(), ex);
    } 
    return originalResultSet;
  }
  
  public <T extends com.mysql.cj.protocol.Resultset> T preProcess(Supplier<String> sql, Query interceptedQuery) {
    return null;
  }
  
  void pauseCounters() {
    this.countStatements = false;
  }
  
  void resumeCounters() {
    this.countStatements = true;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\jdbc\ha\LoadBalancedAutoCommitInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
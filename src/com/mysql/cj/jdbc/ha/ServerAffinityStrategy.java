package com.mysql.cj.jdbc.ha;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.util.StringUtils;
import java.lang.reflect.InvocationHandler;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ServerAffinityStrategy extends RandomBalanceStrategy {
  public String[] affinityOrderedServers = null;
  
  public ServerAffinityStrategy(String affinityOrdervers) {
    if (!StringUtils.isNullOrEmpty(affinityOrdervers))
      this.affinityOrderedServers = affinityOrdervers.split(","); 
  }
  
  public ConnectionImpl pickConnection(InvocationHandler proxy, List<String> configuredHosts, Map<String, JdbcConnection> liveConnections, long[] responseTimes, int numRetries) throws SQLException {
    if (this.affinityOrderedServers == null)
      return super.pickConnection(proxy, configuredHosts, liveConnections, responseTimes, numRetries); 
    Map<String, Long> blockList = ((LoadBalancedConnectionProxy)proxy).getGlobalBlocklist();
    for (String host : this.affinityOrderedServers) {
      if (configuredHosts.contains(host) && !blockList.containsKey(host)) {
        ConnectionImpl conn = (ConnectionImpl)liveConnections.get(host);
        if (conn != null)
          return conn; 
        try {
          conn = ((LoadBalancedConnectionProxy)proxy).createConnectionForHost(host);
          return conn;
        } catch (SQLException sqlEx) {
          if (((LoadBalancedConnectionProxy)proxy).shouldExceptionTriggerConnectionSwitch(sqlEx))
            ((LoadBalancedConnectionProxy)proxy).addToGlobalBlocklist(host); 
        } 
      } 
    } 
    return super.pickConnection(proxy, configuredHosts, liveConnections, responseTimes, numRetries);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\jdbc\ha\ServerAffinityStrategy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.JdbcConnection;
import java.lang.reflect.InvocationHandler;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SequentialBalanceStrategy implements BalanceStrategy {
  private int currentHostIndex = -1;
  
  public ConnectionImpl pickConnection(InvocationHandler proxy, List<String> configuredHosts, Map<String, JdbcConnection> liveConnections, long[] responseTimes, int numRetries) throws SQLException {
    int numHosts = configuredHosts.size();
    SQLException ex = null;
    Map<String, Long> blockList = ((LoadBalancedConnectionProxy)proxy).getGlobalBlocklist();
    for (int attempts = 0; attempts < numRetries; attempts++) {
      if (numHosts == 1) {
        this.currentHostIndex = 0;
      } else if (this.currentHostIndex == -1) {
        int random = (int)Math.floor(Math.random() * numHosts);
        int i;
        for (i = random; i < numHosts; i++) {
          if (!blockList.containsKey(configuredHosts.get(i))) {
            this.currentHostIndex = i;
            break;
          } 
        } 
        if (this.currentHostIndex == -1)
          for (i = 0; i < random; i++) {
            if (!blockList.containsKey(configuredHosts.get(i))) {
              this.currentHostIndex = i;
              break;
            } 
          }  
        if (this.currentHostIndex == -1) {
          blockList = ((LoadBalancedConnectionProxy)proxy).getGlobalBlocklist();
          try {
            Thread.sleep(250L);
          } catch (InterruptedException interruptedException) {}
          continue;
        } 
      } else {
        int i = this.currentHostIndex + 1;
        boolean foundGoodHost = false;
        for (; i < numHosts; i++) {
          if (!blockList.containsKey(configuredHosts.get(i))) {
            this.currentHostIndex = i;
            foundGoodHost = true;
            break;
          } 
        } 
        if (!foundGoodHost)
          for (i = 0; i < this.currentHostIndex; i++) {
            if (!blockList.containsKey(configuredHosts.get(i))) {
              this.currentHostIndex = i;
              foundGoodHost = true;
              break;
            } 
          }  
        if (!foundGoodHost) {
          blockList = ((LoadBalancedConnectionProxy)proxy).getGlobalBlocklist();
          try {
            Thread.sleep(250L);
          } catch (InterruptedException interruptedException) {}
          continue;
        } 
      } 
      String hostPortSpec = configuredHosts.get(this.currentHostIndex);
      ConnectionImpl conn = (ConnectionImpl)liveConnections.get(hostPortSpec);
      if (conn == null)
        try {
          conn = ((LoadBalancedConnectionProxy)proxy).createConnectionForHost(hostPortSpec);
        } catch (SQLException sqlEx) {
          ex = sqlEx;
          if (((LoadBalancedConnectionProxy)proxy).shouldExceptionTriggerConnectionSwitch(sqlEx)) {
            ((LoadBalancedConnectionProxy)proxy).addToGlobalBlocklist(hostPortSpec);
            try {
              Thread.sleep(250L);
            } catch (InterruptedException interruptedException) {}
          } else {
            throw sqlEx;
          } 
        }  
      return conn;
    } 
    if (ex != null)
      throw ex; 
    return null;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\jdbc\ha\SequentialBalanceStrategy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
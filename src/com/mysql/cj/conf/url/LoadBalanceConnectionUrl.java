package com.mysql.cj.conf.url;

import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.ConnectionUrlParser;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.util.StringUtils;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class LoadBalanceConnectionUrl extends ConnectionUrl {
  public LoadBalanceConnectionUrl(ConnectionUrlParser connStrParser, Properties info) {
    super(connStrParser, info);
    this.type = Type.LOADBALANCE_CONNECTION;
  }
  
  public LoadBalanceConnectionUrl(List<HostInfo> hosts, Map<String, String> properties) {
    this.originalConnStr = Type.LOADBALANCE_CONNECTION.getScheme() + "//**internally_generated**" + System.currentTimeMillis() + "**";
    this.originalDatabase = properties.containsKey(PropertyKey.DBNAME.getKeyName()) ? properties.get(PropertyKey.DBNAME.getKeyName()) : "";
    this.type = Type.LOADBALANCE_CONNECTION;
    this.properties.putAll(properties);
    injectPerTypeProperties(this.properties);
    setupPropertiesTransformer();
    hosts.stream().map(this::fixHostInfo).forEach(this.hosts::add);
  }
  
  protected void injectPerTypeProperties(Map<String, String> props) {
    if (props.containsKey(PropertyKey.loadBalanceAutoCommitStatementThreshold.getKeyName()))
      try {
        int autoCommitSwapThreshold = Integer.parseInt(props.get(PropertyKey.loadBalanceAutoCommitStatementThreshold.getKeyName()));
        if (autoCommitSwapThreshold > 0) {
          String queryInterceptors = props.get(PropertyKey.queryInterceptors.getKeyName());
          String lbi = "com.mysql.cj.jdbc.ha.LoadBalancedAutoCommitInterceptor";
          if (StringUtils.isNullOrEmpty(queryInterceptors)) {
            props.put(PropertyKey.queryInterceptors.getKeyName(), lbi);
          } else {
            props.put(PropertyKey.queryInterceptors.getKeyName(), queryInterceptors + "," + lbi);
          } 
        } 
      } catch (Throwable throwable) {} 
  }
  
  public List<String> getHostInfoListAsHostPortPairs() {
    return (List<String>)this.hosts.stream().map(hi -> hi.getHostPortPair()).collect(Collectors.toList());
  }
  
  public List<HostInfo> getHostInfoListFromHostPortPairs(Collection<String> hostPortPairs) {
    return (List<HostInfo>)hostPortPairs.stream().map(this::getHostOrSpawnIsolated).collect(Collectors.toList());
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\con\\url\LoadBalanceConnectionUrl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
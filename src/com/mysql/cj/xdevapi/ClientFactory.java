package com.mysql.cj.xdevapi;

import java.util.Properties;

public class ClientFactory {
  public Client getClient(String url, String clientPropsJson) {
    return new ClientImpl(url, clientPropsJson);
  }
  
  public Client getClient(String url, Properties clientProps) {
    return new ClientImpl(url, clientProps);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\ClientFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
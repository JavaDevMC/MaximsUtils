package com.mysql.cj.xdevapi;

public interface Client {
  Session getSession();
  
  void close();
  
  public enum ClientProperty {
    POOLING_ENABLED("pooling.enabled"),
    POOLING_MAX_SIZE("pooling.maxSize"),
    POOLING_MAX_IDLE_TIME("pooling.maxIdleTime"),
    POOLING_QUEUE_TIMEOUT("pooling.queueTimeout");
    
    private String keyName = "";
    
    ClientProperty(String keyName) {
      this.keyName = keyName;
    }
    
    public String getKeyName() {
      return this.keyName;
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\Client.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
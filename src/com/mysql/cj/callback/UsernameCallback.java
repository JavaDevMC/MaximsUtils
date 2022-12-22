package com.mysql.cj.callback;

public class UsernameCallback implements MysqlCallback {
  private String username;
  
  public UsernameCallback(String username) {
    this.username = username;
  }
  
  public String getUsername() {
    return this.username;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\callback\UsernameCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.mysql.cj.exceptions;

public class DataReadException extends CJException {
  private static final long serialVersionUID = 1684265521187171525L;
  
  public DataReadException(Exception cause) {
    super(cause);
    setSQLState("S1009");
  }
  
  public DataReadException(String msg) {
    super(msg);
    setSQLState("S1009");
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\exceptions\DataReadException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.mysql.cj.exceptions;

public class UnsupportedConnectionStringException extends CJException {
  private static final long serialVersionUID = 3991597077197801820L;
  
  public UnsupportedConnectionStringException() {
    setSQLState("S1009");
  }
  
  public UnsupportedConnectionStringException(String message) {
    super(message);
    setSQLState("S1009");
  }
  
  public UnsupportedConnectionStringException(String message, Throwable cause) {
    super(message, cause);
    setSQLState("S1009");
  }
  
  public UnsupportedConnectionStringException(Throwable cause) {
    super(cause);
    setSQLState("S1009");
  }
  
  public UnsupportedConnectionStringException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    setSQLState("S1009");
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\exceptions\UnsupportedConnectionStringException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
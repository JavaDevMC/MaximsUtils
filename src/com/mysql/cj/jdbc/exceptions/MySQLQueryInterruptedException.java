package com.mysql.cj.jdbc.exceptions;

import java.sql.SQLNonTransientException;

public class MySQLQueryInterruptedException extends SQLNonTransientException {
  private static final long serialVersionUID = -8714521137662613517L;
  
  public MySQLQueryInterruptedException() {}
  
  public MySQLQueryInterruptedException(String reason, String SQLState, int vendorCode) {
    super(reason, SQLState, vendorCode);
  }
  
  public MySQLQueryInterruptedException(String reason, String SQLState) {
    super(reason, SQLState);
  }
  
  public MySQLQueryInterruptedException(String reason) {
    super(reason);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\jdbc\exceptions\MySQLQueryInterruptedException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
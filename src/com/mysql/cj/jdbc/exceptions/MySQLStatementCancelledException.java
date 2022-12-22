package com.mysql.cj.jdbc.exceptions;

import com.mysql.cj.Messages;
import java.sql.SQLNonTransientException;

public class MySQLStatementCancelledException extends SQLNonTransientException {
  static final long serialVersionUID = -8762717748377197378L;
  
  public MySQLStatementCancelledException(String reason, String SQLState, int vendorCode) {
    super(reason, SQLState, vendorCode);
  }
  
  public MySQLStatementCancelledException(String reason, String SQLState) {
    super(reason, SQLState);
  }
  
  public MySQLStatementCancelledException(String reason) {
    super(reason);
  }
  
  public MySQLStatementCancelledException() {
    super(Messages.getString("MySQLStatementCancelledException.0"));
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\jdbc\exceptions\MySQLStatementCancelledException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
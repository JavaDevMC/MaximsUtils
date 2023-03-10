package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import java.util.Iterator;

public interface RowList extends Iterator<Row> {
  public static final int RESULT_SET_SIZE_UNKNOWN = -1;
  
  default Row previous() {
    throw (CJOperationNotSupportedException)ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
  }
  
  default Row get(int n) {
    throw (CJOperationNotSupportedException)ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
  }
  
  default int getPosition() {
    throw (CJOperationNotSupportedException)ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
  }
  
  default int size() {
    return -1;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\result\RowList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
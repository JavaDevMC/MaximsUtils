package com.mysql.cj.exceptions;

public class DataConversionException extends DataReadException {
  private static final long serialVersionUID = -863576663404236982L;
  
  public DataConversionException(String msg) {
    super(msg);
    setSQLState("22018");
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\exceptions\DataConversionException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
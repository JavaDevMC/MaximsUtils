package com.mysql.cj.exceptions;

public class NumberOutOfRange extends DataReadException {
  private static final long serialVersionUID = -61091413023651438L;
  
  public NumberOutOfRange(String msg) {
    super(msg);
    setSQLState("22003");
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\exceptions\NumberOutOfRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
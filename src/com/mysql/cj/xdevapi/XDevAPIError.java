package com.mysql.cj.xdevapi;

import com.mysql.cj.exceptions.CJException;

public class XDevAPIError extends CJException {
  private static final long serialVersionUID = 9102723045325569686L;
  
  public XDevAPIError(String message) {
    super(message);
  }
  
  public XDevAPIError(String message, Throwable t) {
    super(message, t);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\XDevAPIError.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
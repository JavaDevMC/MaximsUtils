package com.google.protobuf;

public class ServiceException extends Exception {
  private static final long serialVersionUID = -1219262335729891920L;
  
  public ServiceException(String message) {
    super(message);
  }
  
  public ServiceException(Throwable cause) {
    super(cause);
  }
  
  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\ServiceException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
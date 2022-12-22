package com.google.gson;

public final class JsonSyntaxException extends JsonParseException {
  private static final long serialVersionUID = 1L;
  
  public JsonSyntaxException(String msg) {
    super(msg);
  }
  
  public JsonSyntaxException(String msg, Throwable cause) {
    super(msg, cause);
  }
  
  public JsonSyntaxException(Throwable cause) {
    super(cause);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\gson\JsonSyntaxException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
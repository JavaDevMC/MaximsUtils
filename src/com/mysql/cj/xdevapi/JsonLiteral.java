package com.mysql.cj.xdevapi;

public enum JsonLiteral implements JsonValue {
  TRUE("true"),
  FALSE("false"),
  NULL("null");
  
  public final String value;
  
  JsonLiteral(String val) {
    this.value = val;
  }
  
  public String toString() {
    return this.value;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\JsonLiteral.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
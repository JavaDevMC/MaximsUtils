package com.mysql.cj;

class Collation {
  public final int index;
  
  public final String collationName;
  
  public final int priority;
  
  public final MysqlCharset mysqlCharset;
  
  public Collation(int index, String collationName, int priority, String charsetName) {
    this.index = index;
    this.collationName = collationName;
    this.priority = priority;
    this.mysqlCharset = CharsetMapping.getStaticMysqlCharsetByName(charsetName);
  }
  
  public String toString() {
    StringBuilder asString = new StringBuilder();
    asString.append("[");
    asString.append("index=");
    asString.append(this.index);
    asString.append(",collationName=");
    asString.append(this.collationName);
    asString.append(",charsetName=");
    asString.append(this.mysqlCharset.charsetName);
    asString.append(",javaCharsetName=");
    asString.append(this.mysqlCharset.getMatchingJavaEncoding(null));
    asString.append("]");
    return asString.toString();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\Collation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
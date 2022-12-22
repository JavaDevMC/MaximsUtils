package com.mysql.cj.xdevapi;

public class Expression {
  private String expressionString;
  
  public Expression(String expressionString) {
    this.expressionString = expressionString;
  }
  
  public String getExpressionString() {
    return this.expressionString;
  }
  
  public static Expression expr(String expressionString) {
    return new Expression(expressionString);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\Expression.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
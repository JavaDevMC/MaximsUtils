package com.mysql.cj.xdevapi;

public class InsertResultBuilder extends UpdateResultBuilder<InsertResult> {
  public InsertResult build() {
    return new InsertResultImpl(this.statementExecuteOkBuilder.build());
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\InsertResultBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
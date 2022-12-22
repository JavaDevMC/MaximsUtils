package com.mysql.cj.xdevapi;

public class AddResultBuilder extends UpdateResultBuilder<AddResult> {
  public AddResult build() {
    return new AddResultImpl(this.statementExecuteOkBuilder.build());
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\AddResultBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
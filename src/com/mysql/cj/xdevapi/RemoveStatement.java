package com.mysql.cj.xdevapi;

public interface RemoveStatement extends Statement<RemoveStatement, Result> {
  @Deprecated
  RemoveStatement orderBy(String... paramVarArgs);
  
  RemoveStatement sort(String... paramVarArgs);
  
  RemoveStatement limit(long paramLong);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\RemoveStatement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
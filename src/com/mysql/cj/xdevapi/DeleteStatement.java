package com.mysql.cj.xdevapi;

public interface DeleteStatement extends Statement<DeleteStatement, Result> {
  DeleteStatement where(String paramString);
  
  DeleteStatement orderBy(String... paramVarArgs);
  
  DeleteStatement limit(long paramLong);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\DeleteStatement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
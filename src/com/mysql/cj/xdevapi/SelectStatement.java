package com.mysql.cj.xdevapi;

public interface SelectStatement extends Statement<SelectStatement, RowResult> {
  SelectStatement where(String paramString);
  
  SelectStatement groupBy(String... paramVarArgs);
  
  SelectStatement having(String paramString);
  
  SelectStatement orderBy(String... paramVarArgs);
  
  SelectStatement limit(long paramLong);
  
  SelectStatement offset(long paramLong);
  
  SelectStatement lockShared();
  
  SelectStatement lockShared(LockContention paramLockContention);
  
  SelectStatement lockExclusive();
  
  SelectStatement lockExclusive(LockContention paramLockContention);
  
  FilterParams getFilterParams();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\SelectStatement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
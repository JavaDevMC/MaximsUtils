package com.mysql.cj.xdevapi;

public interface AddStatement extends Statement<AddStatement, AddResult> {
  AddStatement add(String paramString);
  
  AddStatement add(DbDoc... paramVarArgs);
  
  boolean isUpsert();
  
  AddStatement setUpsert(boolean paramBoolean);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\AddStatement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.protocol.x.StatementExecuteOk;

public class InsertResultImpl extends UpdateResult implements InsertResult {
  public InsertResultImpl(StatementExecuteOk ok) {
    super(ok);
  }
  
  public Long getAutoIncrementValue() {
    return this.ok.getLastInsertId();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\InsertResultImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
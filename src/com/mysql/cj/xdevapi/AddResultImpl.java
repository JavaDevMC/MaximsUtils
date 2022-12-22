package com.mysql.cj.xdevapi;

import com.mysql.cj.protocol.x.StatementExecuteOk;
import java.util.List;

public class AddResultImpl extends UpdateResult implements AddResult {
  public AddResultImpl(StatementExecuteOk ok) {
    super(ok);
  }
  
  public List<String> getGeneratedIds() {
    return this.ok.getGeneratedIds();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\AddResultImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.ProtocolEntityFactory;

public class StatementExecuteOkFactory implements ProtocolEntityFactory<StatementExecuteOk, XMessage> {
  public StatementExecuteOk createFromMessage(XMessage message) {
    return new StatementExecuteOk();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\x\StatementExecuteOkFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.x.protobuf.MysqlxResultset;

public class XProtocolRowFactory implements ProtocolEntityFactory<XProtocolRow, XMessage> {
  public XProtocolRow createFromMessage(XMessage message) {
    return new XProtocolRow(MysqlxResultset.Row.class.cast(message.getMessage()));
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\x\XProtocolRowFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
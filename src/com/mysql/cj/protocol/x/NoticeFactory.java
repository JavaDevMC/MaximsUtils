package com.mysql.cj.protocol.x;

import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.ProtocolEntityFactory;

public class NoticeFactory implements ProtocolEntityFactory<Notice, XMessage> {
  public Notice createFromMessage(XMessage message) {
    return Notice.getInstance(message);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\x\NoticeFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
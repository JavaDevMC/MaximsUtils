package com.mysql.cj.protocol.x;

import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.ProtocolEntityFactory;

public class FetchDoneMoreResultsFactory implements ProtocolEntityFactory<FetchDoneMoreResults, XMessage> {
  public FetchDoneMoreResults createFromMessage(XMessage message) {
    return new FetchDoneMoreResults();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\x\FetchDoneMoreResultsFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ResultsetRows;
import com.mysql.cj.protocol.a.result.NativeResultset;
import com.mysql.cj.protocol.a.result.OkPacket;

public class ResultsetFactory implements ProtocolEntityFactory<Resultset, NativePacketPayload> {
  private Resultset.Type type = Resultset.Type.FORWARD_ONLY;
  
  private Resultset.Concurrency concurrency = Resultset.Concurrency.READ_ONLY;
  
  public ResultsetFactory(Resultset.Type type, Resultset.Concurrency concurrency) {
    this.type = type;
    this.concurrency = concurrency;
  }
  
  public Resultset.Type getResultSetType() {
    return this.type;
  }
  
  public Resultset.Concurrency getResultSetConcurrency() {
    return this.concurrency;
  }
  
  public Resultset createFromProtocolEntity(ProtocolEntity protocolEntity) {
    if (protocolEntity instanceof OkPacket)
      return (Resultset)new NativeResultset((OkPacket)protocolEntity); 
    if (protocolEntity instanceof ResultsetRows)
      return (Resultset)new NativeResultset((ResultsetRows)protocolEntity); 
    throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, "Unknown ProtocolEntity class " + protocolEntity);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\a\ResultsetFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
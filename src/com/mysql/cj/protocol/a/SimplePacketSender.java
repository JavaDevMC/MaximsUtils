package com.mysql.cj.protocol.a;

import com.mysql.cj.protocol.MessageSender;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class SimplePacketSender implements MessageSender<NativePacketPayload> {
  private BufferedOutputStream outputStream;
  
  public SimplePacketSender(BufferedOutputStream outputStream) {
    this.outputStream = outputStream;
  }
  
  public void send(byte[] packet, int packetLen, byte packetSequence) throws IOException {
    PacketSplitter packetSplitter = new PacketSplitter(packetLen);
    while (packetSplitter.nextPacket()) {
      this.outputStream.write(NativeUtils.encodeMysqlThreeByteInteger(packetSplitter.getPacketLen()));
      packetSequence = (byte)(packetSequence + 1);
      this.outputStream.write(packetSequence);
      this.outputStream.write(packet, packetSplitter.getOffset(), packetSplitter.getPacketLen());
    } 
    this.outputStream.flush();
  }
  
  public MessageSender<NativePacketPayload> undecorateAll() {
    return this;
  }
  
  public MessageSender<NativePacketPayload> undecorate() {
    return this;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\a\SimplePacketSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
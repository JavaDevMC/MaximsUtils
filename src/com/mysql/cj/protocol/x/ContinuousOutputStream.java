package com.mysql.cj.protocol.x;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ContinuousOutputStream extends FilterOutputStream {
  protected ContinuousOutputStream(OutputStream out) {
    super(out);
  }
  
  public void close() throws IOException {
    flush();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\x\ContinuousOutputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
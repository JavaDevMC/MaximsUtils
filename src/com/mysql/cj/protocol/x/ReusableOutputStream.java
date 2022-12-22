package com.mysql.cj.protocol.x;

import java.io.FilterOutputStream;
import java.io.OutputStream;

public class ReusableOutputStream extends FilterOutputStream {
  protected ReusableOutputStream(OutputStream out) {
    super(out);
  }
  
  public OutputStream setOutputStream(OutputStream newOut) {
    OutputStream previousOut = this.out;
    this.out = newOut;
    return previousOut;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\x\ReusableOutputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
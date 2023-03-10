package com.mysql.cj.protocol;

import java.io.CharArrayWriter;

public class WatchableWriter extends CharArrayWriter {
  private WriterWatcher watcher;
  
  public void close() {
    super.close();
    if (this.watcher != null)
      this.watcher.writerClosed(this); 
  }
  
  public void setWatcher(WriterWatcher watcher) {
    this.watcher = watcher;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\WatchableWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
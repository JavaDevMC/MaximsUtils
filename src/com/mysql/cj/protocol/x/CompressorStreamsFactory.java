package com.mysql.cj.protocol.x;

import com.mysql.cj.Messages;
import com.mysql.cj.util.Util;
import java.io.InputStream;
import java.io.OutputStream;

public class CompressorStreamsFactory {
  private CompressionAlgorithm compressionAlgorithm;
  
  private InputStream compressorInputStreamInstance = null;
  
  private ContinuousInputStream underlyingInputStream = null;
  
  private OutputStream compressorOutputStreamInstance = null;
  
  private ReusableOutputStream underlyingOutputStream = null;
  
  public CompressorStreamsFactory(CompressionAlgorithm algorithm) {
    this.compressionAlgorithm = algorithm;
  }
  
  public CompressionMode getCompressionMode() {
    return this.compressionAlgorithm.getCompressionMode();
  }
  
  public boolean areCompressedStreamsContinuous() {
    return (getCompressionMode() == CompressionMode.STREAM);
  }
  
  public InputStream getInputStreamInstance(InputStream in) {
    InputStream underlyingIn = in;
    if (areCompressedStreamsContinuous()) {
      if (this.compressorInputStreamInstance != null) {
        this.underlyingInputStream.addInputStream(underlyingIn);
        return this.compressorInputStreamInstance;
      } 
      this.underlyingInputStream = new ContinuousInputStream(underlyingIn);
      underlyingIn = this.underlyingInputStream;
    } 
    InputStream compressionIn = (InputStream)Util.getInstance(this.compressionAlgorithm.getInputStreamClass().getName(), new Class[] { InputStream.class }, new Object[] { underlyingIn }, null, 
        Messages.getString("Protocol.Compression.IoFactory.0", new Object[] { this.compressionAlgorithm.getInputStreamClass().getName(), this.compressionAlgorithm }));
    if (areCompressedStreamsContinuous())
      this.compressorInputStreamInstance = compressionIn; 
    return compressionIn;
  }
  
  public OutputStream getOutputStreamInstance(OutputStream out) {
    OutputStream underlyingOut = out;
    if (areCompressedStreamsContinuous()) {
      if (this.compressorOutputStreamInstance != null) {
        this.underlyingOutputStream.setOutputStream(underlyingOut);
        return this.compressorOutputStreamInstance;
      } 
      this.underlyingOutputStream = new ReusableOutputStream(underlyingOut);
      underlyingOut = this.underlyingOutputStream;
    } 
    OutputStream compressionOut = (OutputStream)Util.getInstance(this.compressionAlgorithm.getOutputStreamClass().getName(), new Class[] { OutputStream.class }, new Object[] { underlyingOut }, null, 
        Messages.getString("Protocol.Compression.IoFactory.1", new Object[] { this.compressionAlgorithm.getOutputStreamClass().getName(), this.compressionAlgorithm }));
    if (areCompressedStreamsContinuous()) {
      compressionOut = new ContinuousOutputStream(compressionOut);
      this.compressorOutputStreamInstance = compressionOut;
    } 
    return compressionOut;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\x\CompressorStreamsFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
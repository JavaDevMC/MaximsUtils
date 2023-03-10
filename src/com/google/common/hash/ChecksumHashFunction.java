package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.zip.Checksum;

@Immutable
@ElementTypesAreNonnullByDefault
final class ChecksumHashFunction extends AbstractHashFunction implements Serializable {
  private final ImmutableSupplier<? extends Checksum> checksumSupplier;
  
  private final int bits;
  
  private final String toString;
  
  private static final long serialVersionUID = 0L;
  
  ChecksumHashFunction(ImmutableSupplier<? extends Checksum> checksumSupplier, int bits, String toString) {
    this.checksumSupplier = (ImmutableSupplier<? extends Checksum>)Preconditions.checkNotNull(checksumSupplier);
    Preconditions.checkArgument((bits == 32 || bits == 64), "bits (%s) must be either 32 or 64", bits);
    this.bits = bits;
    this.toString = (String)Preconditions.checkNotNull(toString);
  }
  
  public int bits() {
    return this.bits;
  }
  
  public Hasher newHasher() {
    return new ChecksumHasher((Checksum)this.checksumSupplier.get());
  }
  
  public String toString() {
    return this.toString;
  }
  
  private final class ChecksumHasher extends AbstractByteHasher {
    private final Checksum checksum;
    
    private ChecksumHasher(Checksum checksum) {
      this.checksum = (Checksum)Preconditions.checkNotNull(checksum);
    }
    
    protected void update(byte b) {
      this.checksum.update(b);
    }
    
    protected void update(byte[] bytes, int off, int len) {
      this.checksum.update(bytes, off, len);
    }
    
    public HashCode hash() {
      long value = this.checksum.getValue();
      if (ChecksumHashFunction.this.bits == 32)
        return HashCode.fromInt((int)value); 
      return HashCode.fromLong(value);
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\hash\ChecksumHashFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public final class CharStreams {
  private static final int DEFAULT_BUF_SIZE = 2048;
  
  static CharBuffer createBuffer() {
    return CharBuffer.allocate(2048);
  }
  
  @CanIgnoreReturnValue
  public static long copy(Readable from, Appendable to) throws IOException {
    if (from instanceof Reader) {
      if (to instanceof StringBuilder)
        return copyReaderToBuilder((Reader)from, (StringBuilder)to); 
      return copyReaderToWriter((Reader)from, asWriter(to));
    } 
    Preconditions.checkNotNull(from);
    Preconditions.checkNotNull(to);
    long total = 0L;
    CharBuffer buf = createBuffer();
    while (from.read(buf) != -1) {
      Java8Compatibility.flip(buf);
      to.append(buf);
      total += buf.remaining();
      Java8Compatibility.clear(buf);
    } 
    return total;
  }
  
  @CanIgnoreReturnValue
  static long copyReaderToBuilder(Reader from, StringBuilder to) throws IOException {
    Preconditions.checkNotNull(from);
    Preconditions.checkNotNull(to);
    char[] buf = new char[2048];
    long total = 0L;
    int nRead;
    while ((nRead = from.read(buf)) != -1) {
      to.append(buf, 0, nRead);
      total += nRead;
    } 
    return total;
  }
  
  @CanIgnoreReturnValue
  static long copyReaderToWriter(Reader from, Writer to) throws IOException {
    Preconditions.checkNotNull(from);
    Preconditions.checkNotNull(to);
    char[] buf = new char[2048];
    long total = 0L;
    int nRead;
    while ((nRead = from.read(buf)) != -1) {
      to.write(buf, 0, nRead);
      total += nRead;
    } 
    return total;
  }
  
  public static String toString(Readable r) throws IOException {
    return toStringBuilder(r).toString();
  }
  
  private static StringBuilder toStringBuilder(Readable r) throws IOException {
    StringBuilder sb = new StringBuilder();
    if (r instanceof Reader) {
      copyReaderToBuilder((Reader)r, sb);
    } else {
      copy(r, sb);
    } 
    return sb;
  }
  
  @Beta
  public static List<String> readLines(Readable r) throws IOException {
    List<String> result = new ArrayList<>();
    LineReader lineReader = new LineReader(r);
    String line;
    while ((line = lineReader.readLine()) != null)
      result.add(line); 
    return result;
  }
  
  @ParametricNullness
  @Beta
  @CanIgnoreReturnValue
  public static <T> T readLines(Readable readable, LineProcessor<T> processor) throws IOException {
    Preconditions.checkNotNull(readable);
    Preconditions.checkNotNull(processor);
    LineReader lineReader = new LineReader(readable);
    String line;
    do {
    
    } while ((line = lineReader.readLine()) != null && 
      processor.processLine(line));
    return processor.getResult();
  }
  
  @Beta
  @CanIgnoreReturnValue
  public static long exhaust(Readable readable) throws IOException {
    long total = 0L;
    CharBuffer buf = createBuffer();
    long read;
    while ((read = readable.read(buf)) != -1L) {
      total += read;
      Java8Compatibility.clear(buf);
    } 
    return total;
  }
  
  @Beta
  public static void skipFully(Reader reader, long n) throws IOException {
    Preconditions.checkNotNull(reader);
    while (n > 0L) {
      long amt = reader.skip(n);
      if (amt == 0L)
        throw new EOFException(); 
      n -= amt;
    } 
  }
  
  @Beta
  public static Writer nullWriter() {
    return NullWriter.INSTANCE;
  }
  
  private static final class NullWriter extends Writer {
    private static final NullWriter INSTANCE = new NullWriter();
    
    public void write(int c) {}
    
    public void write(char[] cbuf) {
      Preconditions.checkNotNull(cbuf);
    }
    
    public void write(char[] cbuf, int off, int len) {
      Preconditions.checkPositionIndexes(off, off + len, cbuf.length);
    }
    
    public void write(String str) {
      Preconditions.checkNotNull(str);
    }
    
    public void write(String str, int off, int len) {
      Preconditions.checkPositionIndexes(off, off + len, str.length());
    }
    
    public Writer append(@CheckForNull CharSequence csq) {
      return this;
    }
    
    public Writer append(@CheckForNull CharSequence csq, int start, int end) {
      Preconditions.checkPositionIndexes(start, end, (csq == null) ? "null".length() : csq.length());
      return this;
    }
    
    public Writer append(char c) {
      return this;
    }
    
    public void flush() {}
    
    public void close() {}
    
    public String toString() {
      return "CharStreams.nullWriter()";
    }
  }
  
  @Beta
  public static Writer asWriter(Appendable target) {
    if (target instanceof Writer)
      return (Writer)target; 
    return new AppendableWriter(target);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\io\CharStreams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

@ElementTypesAreNonnullByDefault
@Beta
@GwtIncompatible
public final class Resources {
  public static ByteSource asByteSource(URL url) {
    return new UrlByteSource(url);
  }
  
  private static final class UrlByteSource extends ByteSource {
    private final URL url;
    
    private UrlByteSource(URL url) {
      this.url = (URL)Preconditions.checkNotNull(url);
    }
    
    public InputStream openStream() throws IOException {
      return this.url.openStream();
    }
    
    public String toString() {
      String str = String.valueOf(this.url);
      return (new StringBuilder(24 + String.valueOf(str).length())).append("Resources.asByteSource(").append(str).append(")").toString();
    }
  }
  
  public static CharSource asCharSource(URL url, Charset charset) {
    return asByteSource(url).asCharSource(charset);
  }
  
  public static byte[] toByteArray(URL url) throws IOException {
    return asByteSource(url).read();
  }
  
  public static String toString(URL url, Charset charset) throws IOException {
    return asCharSource(url, charset).read();
  }
  
  @ParametricNullness
  @CanIgnoreReturnValue
  public static <T> T readLines(URL url, Charset charset, LineProcessor<T> callback) throws IOException {
    return asCharSource(url, charset).readLines(callback);
  }
  
  public static List<String> readLines(URL url, Charset charset) throws IOException {
    return readLines(url, charset, new LineProcessor<List<String>>() {
          final List<String> result = Lists.newArrayList();
          
          public boolean processLine(String line) {
            this.result.add(line);
            return true;
          }
          
          public List<String> getResult() {
            return this.result;
          }
        });
  }
  
  public static void copy(URL from, OutputStream to) throws IOException {
    asByteSource(from).copyTo(to);
  }
  
  @CanIgnoreReturnValue
  public static URL getResource(String resourceName) {
    ClassLoader loader = (ClassLoader)MoreObjects.firstNonNull(
        Thread.currentThread().getContextClassLoader(), Resources.class.getClassLoader());
    URL url = loader.getResource(resourceName);
    Preconditions.checkArgument((url != null), "resource %s not found.", resourceName);
    return url;
  }
  
  @CanIgnoreReturnValue
  public static URL getResource(Class<?> contextClass, String resourceName) {
    URL url = contextClass.getResource(resourceName);
    Preconditions.checkArgument((url != null), "resource %s relative to %s not found.", resourceName, contextClass
        .getName());
    return url;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\io\Resources.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
@GwtCompatible
public final class Escapers {
  public static Escaper nullEscaper() {
    return NULL_ESCAPER;
  }
  
  private static final Escaper NULL_ESCAPER = new CharEscaper() {
      public String escape(String string) {
        return (String)Preconditions.checkNotNull(string);
      }
      
      @CheckForNull
      protected char[] escape(char c) {
        return null;
      }
    };
  
  public static Builder builder() {
    return new Builder();
  }
  
  @Beta
  public static final class Builder {
    private final Map<Character, String> replacementMap = new HashMap<>();
    
    private char safeMin = Character.MIN_VALUE;
    
    private char safeMax = Character.MAX_VALUE;
    
    @CheckForNull
    private String unsafeReplacement = null;
    
    @CanIgnoreReturnValue
    public Builder setSafeRange(char safeMin, char safeMax) {
      this.safeMin = safeMin;
      this.safeMax = safeMax;
      return this;
    }
    
    @CanIgnoreReturnValue
    public Builder setUnsafeReplacement(String unsafeReplacement) {
      this.unsafeReplacement = unsafeReplacement;
      return this;
    }
    
    @CanIgnoreReturnValue
    public Builder addEscape(char c, String replacement) {
      Preconditions.checkNotNull(replacement);
      this.replacementMap.put(Character.valueOf(c), replacement);
      return this;
    }
    
    public Escaper build() {
      return new ArrayBasedCharEscaper(this.replacementMap, this.safeMin, this.safeMax) {
          @CheckForNull
          private final char[] replacementChars = (Builder.this.unsafeReplacement != null) ? Builder.this.unsafeReplacement.toCharArray() : null;
          
          @CheckForNull
          protected char[] escapeUnsafe(char c) {
            return this.replacementChars;
          }
        };
    }
    
    private Builder() {}
  }
  
  static UnicodeEscaper asUnicodeEscaper(Escaper escaper) {
    Preconditions.checkNotNull(escaper);
    if (escaper instanceof UnicodeEscaper)
      return (UnicodeEscaper)escaper; 
    if (escaper instanceof CharEscaper)
      return wrap((CharEscaper)escaper); 
    String.valueOf(escaper.getClass().getName());
    throw new IllegalArgumentException((String.valueOf(escaper.getClass().getName()).length() != 0) ? "Cannot create a UnicodeEscaper from: ".concat(String.valueOf(escaper.getClass().getName())) : new String("Cannot create a UnicodeEscaper from: "));
  }
  
  @CheckForNull
  public static String computeReplacement(CharEscaper escaper, char c) {
    return stringOrNull(escaper.escape(c));
  }
  
  @CheckForNull
  public static String computeReplacement(UnicodeEscaper escaper, int cp) {
    return stringOrNull(escaper.escape(cp));
  }
  
  @CheckForNull
  private static String stringOrNull(@CheckForNull char[] in) {
    return (in == null) ? null : new String(in);
  }
  
  private static UnicodeEscaper wrap(final CharEscaper escaper) {
    return new UnicodeEscaper() {
        @CheckForNull
        protected char[] escape(int cp) {
          if (cp < 65536)
            return escaper.escape((char)cp); 
          char[] surrogateChars = new char[2];
          Character.toChars(cp, surrogateChars, 0);
          char[] hiChars = escaper.escape(surrogateChars[0]);
          char[] loChars = escaper.escape(surrogateChars[1]);
          if (hiChars == null && loChars == null)
            return null; 
          int hiCount = (hiChars != null) ? hiChars.length : 1;
          int loCount = (loChars != null) ? loChars.length : 1;
          char[] output = new char[hiCount + loCount];
          if (hiChars != null) {
            for (int n = 0; n < hiChars.length; n++)
              output[n] = hiChars[n]; 
          } else {
            output[0] = surrogateChars[0];
          } 
          if (loChars != null) {
            for (int n = 0; n < loChars.length; n++)
              output[hiCount + n] = loChars[n]; 
          } else {
            output[hiCount] = surrogateChars[1];
          } 
          return output;
        }
      };
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\escape\Escapers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
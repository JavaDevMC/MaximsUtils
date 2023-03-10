package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
@GwtCompatible
public abstract class ArrayBasedCharEscaper extends CharEscaper {
  private final char[][] replacements;
  
  private final int replacementsLength;
  
  private final char safeMin;
  
  private final char safeMax;
  
  protected ArrayBasedCharEscaper(Map<Character, String> replacementMap, char safeMin, char safeMax) {
    this(ArrayBasedEscaperMap.create(replacementMap), safeMin, safeMax);
  }
  
  protected ArrayBasedCharEscaper(ArrayBasedEscaperMap escaperMap, char safeMin, char safeMax) {
    Preconditions.checkNotNull(escaperMap);
    this.replacements = escaperMap.getReplacementArray();
    this.replacementsLength = this.replacements.length;
    if (safeMax < safeMin) {
      safeMax = Character.MIN_VALUE;
      safeMin = Character.MAX_VALUE;
    } 
    this.safeMin = safeMin;
    this.safeMax = safeMax;
  }
  
  public final String escape(String s) {
    Preconditions.checkNotNull(s);
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if ((c < this.replacementsLength && this.replacements[c] != null) || c > this.safeMax || c < this.safeMin)
        return escapeSlow(s, i); 
    } 
    return s;
  }
  
  @CheckForNull
  protected final char[] escape(char c) {
    if (c < this.replacementsLength) {
      char[] chars = this.replacements[c];
      if (chars != null)
        return chars; 
    } 
    if (c >= this.safeMin && c <= this.safeMax)
      return null; 
    return escapeUnsafe(c);
  }
  
  @CheckForNull
  protected abstract char[] escapeUnsafe(char paramChar);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\escape\ArrayBasedCharEscaper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
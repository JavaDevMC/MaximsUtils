package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Queues;
import java.util.Deque;

@GwtCompatible
final class TrieParser {
  private static final Joiner PREFIX_JOINER = Joiner.on("");
  
  static ImmutableMap<String, PublicSuffixType> parseTrie(CharSequence encoded) {
    ImmutableMap.Builder<String, PublicSuffixType> builder = ImmutableMap.builder();
    int encodedLen = encoded.length();
    int idx = 0;
    while (idx < encodedLen)
      idx += doParseTrieToBuilder(Queues.newArrayDeque(), encoded, idx, builder); 
    return builder.build();
  }
  
  private static int doParseTrieToBuilder(Deque<CharSequence> stack, CharSequence encoded, int start, ImmutableMap.Builder<String, PublicSuffixType> builder) {
    int encodedLen = encoded.length();
    int idx = start;
    char c = Character.MIN_VALUE;
    for (; idx < encodedLen; idx++) {
      c = encoded.charAt(idx);
      if (c == '&' || c == '?' || c == '!' || c == ':' || c == ',')
        break; 
    } 
    stack.push(reverse(encoded.subSequence(start, idx)));
    if (c == '!' || c == '?' || c == ':' || c == ',') {
      String domain = PREFIX_JOINER.join(stack);
      if (domain.length() > 0)
        builder.put(domain, PublicSuffixType.fromCode(c)); 
    } 
    idx++;
    if (c != '?' && c != ',')
      while (idx < encodedLen) {
        idx += doParseTrieToBuilder(stack, encoded, idx, builder);
        if (encoded.charAt(idx) == '?' || encoded.charAt(idx) == ',') {
          idx++;
          break;
        } 
      }  
    stack.pop();
    return idx - start;
  }
  
  private static CharSequence reverse(CharSequence s) {
    return (new StringBuilder(s)).reverse();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\thirdparty\publicsuffix\TrieParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
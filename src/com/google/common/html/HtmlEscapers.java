package com.google.common.html;

import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class HtmlEscapers {
  public static Escaper htmlEscaper() {
    return HTML_ESCAPER;
  }
  
  private static final Escaper HTML_ESCAPER = Escapers.builder()
    .addEscape('"', "&quot;")
    
    .addEscape('\'', "&#39;")
    .addEscape('&', "&amp;")
    .addEscape('<', "&lt;")
    .addEscape('>', "&gt;")
    .build();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\html\HtmlEscapers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
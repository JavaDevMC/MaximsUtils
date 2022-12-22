package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@Beta
@GwtCompatible
public enum PublicSuffixType {
  PRIVATE(':', ','),
  REGISTRY('!', '?');
  
  private final char innerNodeCode;
  
  private final char leafNodeCode;
  
  PublicSuffixType(char innerNodeCode, char leafNodeCode) {
    this.innerNodeCode = innerNodeCode;
    this.leafNodeCode = leafNodeCode;
  }
  
  char getLeafNodeCode() {
    return this.leafNodeCode;
  }
  
  char getInnerNodeCode() {
    return this.innerNodeCode;
  }
  
  static PublicSuffixType fromCode(char code) {
    for (PublicSuffixType value : values()) {
      if (value.getInnerNodeCode() == code || value.getLeafNodeCode() == code)
        return value; 
    } 
    throw new IllegalArgumentException((new StringBuilder(38)).append("No enum corresponding to given code: ").append(code).toString());
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\thirdparty\publicsuffix\PublicSuffixType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
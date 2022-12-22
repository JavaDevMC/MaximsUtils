package com.google.protobuf;

interface MutabilityOracle {
  public static final MutabilityOracle IMMUTABLE = new MutabilityOracle() {
      public void ensureMutable() {
        throw new UnsupportedOperationException();
      }
    };
  
  void ensureMutable();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\MutabilityOracle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
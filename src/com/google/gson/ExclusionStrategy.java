package com.google.gson;

public interface ExclusionStrategy {
  boolean shouldSkipField(FieldAttributes paramFieldAttributes);
  
  boolean shouldSkipClass(Class<?> paramClass);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\gson\ExclusionStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package com.google.protobuf;

import java.lang.reflect.Field;

final class OneofInfo {
  private final int id;
  
  private final Field caseField;
  
  private final Field valueField;
  
  public OneofInfo(int id, Field caseField, Field valueField) {
    this.id = id;
    this.caseField = caseField;
    this.valueField = valueField;
  }
  
  public int getId() {
    return this.id;
  }
  
  public Field getCaseField() {
    return this.caseField;
  }
  
  public Field getValueField() {
    return this.valueField;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\OneofInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
package com.mysql.cj.conf;

public class EnumProperty<T extends Enum<T>> extends AbstractRuntimeProperty<T> {
  private static final long serialVersionUID = -60853080911910124L;
  
  protected EnumProperty(PropertyDefinition<T> propertyDefinition) {
    super(propertyDefinition);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\conf\EnumProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
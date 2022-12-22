package com.mysql.cj.conf;

public class StringProperty extends AbstractRuntimeProperty<String> {
  private static final long serialVersionUID = -4141084145739428803L;
  
  protected StringProperty(PropertyDefinition<String> propertyDefinition) {
    super(propertyDefinition);
  }
  
  public String getStringValue() {
    return this.value;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\conf\StringProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
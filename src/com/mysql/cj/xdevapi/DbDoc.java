package com.mysql.cj.xdevapi;

import java.util.Map;

public interface DbDoc extends JsonValue, Map<String, JsonValue> {
  DbDoc add(String paramString, JsonValue paramJsonValue);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\DbDoc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
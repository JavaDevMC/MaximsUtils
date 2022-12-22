package com.mysql.cj.protocol;

import com.mysql.cj.result.Row;

public interface ResultsetRow extends Row, ProtocolEntity {
  default boolean isBinaryEncoded() {
    return false;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\ResultsetRow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
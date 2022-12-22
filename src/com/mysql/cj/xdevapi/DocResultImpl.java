package com.mysql.cj.xdevapi;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.result.RowList;
import java.util.function.Supplier;

public class DocResultImpl extends AbstractDataResult<DbDoc> implements DocResult {
  public DocResultImpl(RowList rows, Supplier<ProtocolEntity> completer, PropertySet pset) {
    super(rows, completer, new DbDocFactory(pset));
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\DocResultImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
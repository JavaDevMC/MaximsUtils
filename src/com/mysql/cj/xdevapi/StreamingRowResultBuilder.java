package com.mysql.cj.xdevapi;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ResultBuilder;
import com.mysql.cj.protocol.x.Notice;
import com.mysql.cj.protocol.x.StatementExecuteOkBuilder;
import com.mysql.cj.protocol.x.XProtocol;
import com.mysql.cj.protocol.x.XProtocolRowInputStream;
import com.mysql.cj.result.DefaultColumnDefinition;
import com.mysql.cj.result.Field;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.RowList;
import java.util.ArrayList;
import java.util.TimeZone;

public class StreamingRowResultBuilder implements ResultBuilder<RowResult> {
  private ArrayList<Field> fields = new ArrayList<>();
  
  private ColumnDefinition metadata;
  
  private RowList rowList = null;
  
  TimeZone defaultTimeZone;
  
  PropertySet pset;
  
  XProtocol protocol;
  
  private StatementExecuteOkBuilder statementExecuteOkBuilder = new StatementExecuteOkBuilder();
  
  public StreamingRowResultBuilder(MysqlxSession sess) {
    this.defaultTimeZone = sess.getServerSession().getDefaultTimeZone();
    this.pset = sess.getPropertySet();
    this.protocol = sess.getProtocol();
  }
  
  public boolean addProtocolEntity(ProtocolEntity entity) {
    if (entity instanceof Field) {
      this.fields.add((Field)entity);
      return false;
    } 
    if (entity instanceof Notice)
      this.statementExecuteOkBuilder.addProtocolEntity(entity); 
    if (this.metadata == null)
      this.metadata = (ColumnDefinition)new DefaultColumnDefinition(this.fields.<Field>toArray(new Field[0])); 
    this.rowList = (entity instanceof Row) ? (RowList)new XProtocolRowInputStream(this.metadata, (Row)entity, this.protocol, n -> this.statementExecuteOkBuilder.addProtocolEntity((ProtocolEntity)n)) : (RowList)new XProtocolRowInputStream(this.metadata, this.protocol, n -> this.statementExecuteOkBuilder.addProtocolEntity((ProtocolEntity)n));
    return true;
  }
  
  public RowResult build() {
    return new RowResultImpl(this.metadata, this.defaultTimeZone, this.rowList, () -> (ProtocolEntity)this.protocol.readQueryResult((ResultBuilder)this.statementExecuteOkBuilder), this.pset);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\StreamingRowResultBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ResultBuilder;
import com.mysql.cj.protocol.x.StatementExecuteOkBuilder;
import com.mysql.cj.result.BufferedRowList;
import com.mysql.cj.result.DefaultColumnDefinition;
import com.mysql.cj.result.Field;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.RowList;
import java.util.ArrayList;
import java.util.List;

public class DocResultBuilder implements ResultBuilder<DocResult> {
  private ArrayList<Field> fields = new ArrayList<>();
  
  private ColumnDefinition metadata;
  
  private List<Row> rows = new ArrayList<>();
  
  private DocResult result;
  
  PropertySet pset;
  
  private StatementExecuteOkBuilder statementExecuteOkBuilder = new StatementExecuteOkBuilder();
  
  public DocResultBuilder(MysqlxSession sess) {
    this.pset = sess.getPropertySet();
  }
  
  public boolean addProtocolEntity(ProtocolEntity entity) {
    if (entity instanceof Field) {
      this.fields.add((Field)entity);
      return false;
    } 
    if (entity instanceof Row) {
      if (this.metadata == null)
        this.metadata = (ColumnDefinition)new DefaultColumnDefinition(this.fields.<Field>toArray(new Field[0])); 
      this.rows.add(((Row)entity).setMetadata(this.metadata));
      return false;
    } 
    if (entity instanceof com.mysql.cj.protocol.x.Notice) {
      this.statementExecuteOkBuilder.addProtocolEntity(entity);
      return false;
    } 
    if (entity instanceof com.mysql.cj.protocol.x.FetchDoneEntity)
      return false; 
    if (entity instanceof com.mysql.cj.protocol.x.StatementExecuteOk)
      return true; 
    throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, "Unexpected protocol entity " + entity);
  }
  
  public DocResult build() {
    this.result = new DocResultImpl((RowList)new BufferedRowList(this.rows), () -> this.statementExecuteOkBuilder.build(), this.pset);
    return this.result;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\DocResultBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
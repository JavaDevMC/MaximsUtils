package com.mysql.cj.protocol.result;

import com.mysql.cj.Messages;
import com.mysql.cj.MysqlType;
import com.mysql.cj.exceptions.DataReadException;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ResultsetRow;
import com.mysql.cj.protocol.ValueDecoder;
import com.mysql.cj.result.Field;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.ValueFactory;

public abstract class AbstractResultsetRow implements ResultsetRow {
  protected ExceptionInterceptor exceptionInterceptor;
  
  protected ColumnDefinition metadata;
  
  protected ValueDecoder valueDecoder;
  
  protected boolean wasNull;
  
  protected AbstractResultsetRow(ExceptionInterceptor exceptionInterceptor) {
    this.exceptionInterceptor = exceptionInterceptor;
  }
  
  private <T> T decodeAndCreateReturnValue(int columnIndex, byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    Field f = this.metadata.getFields()[columnIndex];
    switch (f.getMysqlTypeId()) {
      case 12:
        return (T)this.valueDecoder.decodeDatetime(bytes, offset, length, f.getDecimals(), vf);
      case 7:
        return (T)this.valueDecoder.decodeTimestamp(bytes, offset, length, f.getDecimals(), vf);
      case 10:
        return (T)this.valueDecoder.decodeDate(bytes, offset, length, vf);
      case 11:
        return (T)this.valueDecoder.decodeTime(bytes, offset, length, f.getDecimals(), vf);
      case 1:
        return f.isUnsigned() ? (T)this.valueDecoder.decodeUInt1(bytes, offset, length, vf) : (T)this.valueDecoder.decodeInt1(bytes, offset, length, vf);
      case 13:
        return (T)this.valueDecoder.decodeYear(bytes, offset, length, vf);
      case 2:
        return f.isUnsigned() ? (T)this.valueDecoder.decodeUInt2(bytes, offset, length, vf) : (T)this.valueDecoder.decodeInt2(bytes, offset, length, vf);
      case 3:
        return f.isUnsigned() ? (T)this.valueDecoder.decodeUInt4(bytes, offset, length, vf) : (T)this.valueDecoder.decodeInt4(bytes, offset, length, vf);
      case 9:
        return (T)this.valueDecoder.decodeInt4(bytes, offset, length, vf);
      case 8:
        return f.isUnsigned() ? (T)this.valueDecoder.decodeUInt8(bytes, offset, length, vf) : (T)this.valueDecoder.decodeInt8(bytes, offset, length, vf);
      case 4:
        return (T)this.valueDecoder.decodeFloat(bytes, offset, length, vf);
      case 5:
        return (T)this.valueDecoder.decodeDouble(bytes, offset, length, vf);
      case 0:
      case 246:
        return (T)this.valueDecoder.decodeDecimal(bytes, offset, length, vf);
      case 15:
      case 245:
      case 247:
      case 249:
      case 250:
      case 251:
      case 252:
      case 253:
      case 254:
      case 255:
        return (T)this.valueDecoder.decodeByteArray(bytes, offset, length, f, vf);
      case 248:
        return (T)this.valueDecoder.decodeSet(bytes, offset, length, f, vf);
      case 16:
        return (T)this.valueDecoder.decodeBit(bytes, offset, length, vf);
      case 6:
        return (T)vf.createFromNull();
    } 
    switch (f.getMysqlType()) {
      case TINYINT:
        return (T)this.valueDecoder.decodeInt1(bytes, offset, length, vf);
      case TINYINT_UNSIGNED:
        return (T)this.valueDecoder.decodeUInt1(bytes, offset, length, vf);
      case SMALLINT:
        return (T)this.valueDecoder.decodeInt2(bytes, offset, length, vf);
      case YEAR:
        return (T)this.valueDecoder.decodeYear(bytes, offset, length, vf);
      case SMALLINT_UNSIGNED:
        return (T)this.valueDecoder.decodeUInt2(bytes, offset, length, vf);
      case INT:
      case MEDIUMINT:
        return (T)this.valueDecoder.decodeInt4(bytes, offset, length, vf);
      case INT_UNSIGNED:
      case MEDIUMINT_UNSIGNED:
        return (T)this.valueDecoder.decodeUInt4(bytes, offset, length, vf);
      case BIGINT:
        return (T)this.valueDecoder.decodeInt8(bytes, offset, length, vf);
      case BIGINT_UNSIGNED:
        return (T)this.valueDecoder.decodeUInt8(bytes, offset, length, vf);
      case FLOAT:
      case FLOAT_UNSIGNED:
        return (T)this.valueDecoder.decodeFloat(bytes, offset, length, vf);
      case DOUBLE:
      case DOUBLE_UNSIGNED:
        return (T)this.valueDecoder.decodeDouble(bytes, offset, length, vf);
      case DECIMAL:
      case DECIMAL_UNSIGNED:
        return (T)this.valueDecoder.decodeDecimal(bytes, offset, length, vf);
      case BOOLEAN:
      case VARBINARY:
      case VARCHAR:
      case BINARY:
      case CHAR:
      case TINYBLOB:
      case BLOB:
      case MEDIUMBLOB:
      case LONGBLOB:
      case TINYTEXT:
      case TEXT:
      case MEDIUMTEXT:
      case LONGTEXT:
      case JSON:
      case ENUM:
      case SET:
      case GEOMETRY:
      case UNKNOWN:
        return (T)this.valueDecoder.decodeByteArray(bytes, offset, length, f, vf);
      case BIT:
        return (T)this.valueDecoder.decodeBit(bytes, offset, length, vf);
      case DATETIME:
      case TIMESTAMP:
        return (T)this.valueDecoder.decodeTimestamp(bytes, offset, length, f.getDecimals(), vf);
      case DATE:
        return (T)this.valueDecoder.decodeDate(bytes, offset, length, vf);
      case TIME:
        return (T)this.valueDecoder.decodeTime(bytes, offset, length, f.getDecimals(), vf);
      case NULL:
        return (T)vf.createFromNull();
    } 
    throw new DataReadException(Messages.getString("ResultSet.UnknownSourceType"));
  }
  
  protected <T> T getValueFromBytes(int columnIndex, byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    if (getNull(columnIndex))
      return (T)vf.createFromNull(); 
    T retVal = decodeAndCreateReturnValue(columnIndex, bytes, offset, length, vf);
    this.wasNull = (retVal == null);
    return retVal;
  }
  
  public Row setMetadata(ColumnDefinition f) {
    this.metadata = f;
    return (Row)this;
  }
  
  public boolean wasNull() {
    return this.wasNull;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\result\AbstractResultsetRow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
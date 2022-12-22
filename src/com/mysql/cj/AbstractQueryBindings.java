package com.mysql.cj;

import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.util.TimeUtil;
import com.mysql.cj.util.Util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractQueryBindings<T extends BindValue> implements QueryBindings<T> {
  protected static final byte[] HEX_DIGITS = new byte[] { 
      48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 
      65, 66, 67, 68, 69, 70 };
  
  protected static final LocalDate DEFAULT_DATE = LocalDate.of(1970, 1, 1);
  
  protected static final LocalTime DEFAULT_TIME = LocalTime.of(0, 0);
  
  protected Session session;
  
  protected T[] bindValues;
  
  protected String charEncoding;
  
  protected int numberOfExecutions = 0;
  
  protected RuntimeProperty<Boolean> useStreamLengthsInPrepStmts;
  
  protected RuntimeProperty<Boolean> preserveInstants;
  
  protected RuntimeProperty<Boolean> sendFractionalSeconds;
  
  protected RuntimeProperty<Boolean> sendFractionalSecondsForTime;
  
  private RuntimeProperty<Boolean> treatUtilDateAsTimestamp;
  
  protected boolean isLoadDataQuery = false;
  
  protected ColumnDefinition columnDefinition;
  
  public void setColumnDefinition(ColumnDefinition colDef) {
    this.columnDefinition = colDef;
  }
  
  public boolean isLoadDataQuery() {
    return this.isLoadDataQuery;
  }
  
  public void setLoadDataQuery(boolean isLoadDataQuery) {
    this.isLoadDataQuery = isLoadDataQuery;
  }
  
  public T[] getBindValues() {
    return this.bindValues;
  }
  
  public void setBindValues(T[] bindValues) {
    this.bindValues = bindValues;
  }
  
  public boolean clearBindValues() {
    boolean hadLongData = false;
    if (this.bindValues != null)
      for (int i = 0; i < this.bindValues.length; i++) {
        if (this.bindValues[i] != null && this.bindValues[i].isStream())
          hadLongData = true; 
        this.bindValues[i].reset();
      }  
    return hadLongData;
  }
  
  public void checkAllParametersSet() {
    for (int i = 0; i < this.bindValues.length; i++)
      checkParameterSet(i); 
  }
  
  public int getNumberOfExecutions() {
    return this.numberOfExecutions;
  }
  
  public void setNumberOfExecutions(int numberOfExecutions) {
    this.numberOfExecutions = numberOfExecutions;
  }
  
  public final synchronized void setValue(int paramIndex, byte[] val, MysqlType type) {
    this.bindValues[paramIndex].setByteValue(val);
    this.bindValues[paramIndex].setMysqlType(type);
  }
  
  public final synchronized void setOrigValue(int paramIndex, byte[] val) {
    this.bindValues[paramIndex].setOrigByteValue(val);
  }
  
  public synchronized byte[] getOrigBytes(int parameterIndex) {
    return this.bindValues[parameterIndex].getOrigByteValue();
  }
  
  public final synchronized void setValue(int paramIndex, String val, MysqlType type) {
    byte[] parameterAsBytes = StringUtils.getBytes(val, this.charEncoding);
    setValue(paramIndex, parameterAsBytes, type);
  }
  
  public final void hexEscapeBlock(byte[] buf, NativePacketPayload packet, int size) {
    for (int i = 0; i < size; i++) {
      byte b = buf[i];
      int lowBits = (b & 0xFF) / 16;
      int highBits = (b & 0xFF) % 16;
      packet.writeInteger(NativeConstants.IntegerDataType.INT1, HEX_DIGITS[lowBits]);
      packet.writeInteger(NativeConstants.IntegerDataType.INT1, HEX_DIGITS[highBits]);
    } 
  }
  
  static Map<Class<?>, MysqlType> DEFAULT_MYSQL_TYPES = new HashMap<>();
  
  private byte[] streamConvertBuf;
  
  static {
    DEFAULT_MYSQL_TYPES.put(String.class, MysqlType.VARCHAR);
    DEFAULT_MYSQL_TYPES.put(Date.class, MysqlType.DATE);
    DEFAULT_MYSQL_TYPES.put(Time.class, MysqlType.TIME);
    DEFAULT_MYSQL_TYPES.put(Timestamp.class, MysqlType.TIMESTAMP);
    DEFAULT_MYSQL_TYPES.put(Byte.class, MysqlType.INT);
    DEFAULT_MYSQL_TYPES.put(BigDecimal.class, MysqlType.DECIMAL);
    DEFAULT_MYSQL_TYPES.put(Short.class, MysqlType.SMALLINT);
    DEFAULT_MYSQL_TYPES.put(Integer.class, MysqlType.INT);
    DEFAULT_MYSQL_TYPES.put(Long.class, MysqlType.BIGINT);
    DEFAULT_MYSQL_TYPES.put(Float.class, MysqlType.FLOAT);
    DEFAULT_MYSQL_TYPES.put(Double.class, MysqlType.DOUBLE);
    DEFAULT_MYSQL_TYPES.put(byte[].class, MysqlType.BINARY);
    DEFAULT_MYSQL_TYPES.put(Boolean.class, MysqlType.BOOLEAN);
    DEFAULT_MYSQL_TYPES.put(LocalDate.class, MysqlType.DATE);
    DEFAULT_MYSQL_TYPES.put(LocalTime.class, MysqlType.TIME);
    DEFAULT_MYSQL_TYPES.put(LocalDateTime.class, MysqlType.DATETIME);
    DEFAULT_MYSQL_TYPES.put(OffsetTime.class, MysqlType.TIME);
    DEFAULT_MYSQL_TYPES.put(OffsetDateTime.class, MysqlType.TIMESTAMP);
    DEFAULT_MYSQL_TYPES.put(ZonedDateTime.class, MysqlType.TIMESTAMP);
    DEFAULT_MYSQL_TYPES.put(Duration.class, MysqlType.TIME);
    DEFAULT_MYSQL_TYPES.put(Blob.class, MysqlType.BLOB);
    DEFAULT_MYSQL_TYPES.put(Clob.class, MysqlType.TEXT);
    DEFAULT_MYSQL_TYPES.put(BigInteger.class, MysqlType.BIGINT);
    DEFAULT_MYSQL_TYPES.put(Date.class, MysqlType.TIMESTAMP);
    DEFAULT_MYSQL_TYPES.put(Calendar.class, MysqlType.TIMESTAMP);
    DEFAULT_MYSQL_TYPES.put(InputStream.class, MysqlType.BLOB);
  }
  
  public void setTimestamp(int parameterIndex, Timestamp x, MysqlType targetMysqlType) {
    int fractLen = -1;
    if (!this.session.getServerSession().getCapabilities().serverSupportsFracSecs() || !((Boolean)this.sendFractionalSeconds.getValue()).booleanValue()) {
      fractLen = 0;
    } else if (this.columnDefinition != null && parameterIndex <= (this.columnDefinition.getFields()).length && parameterIndex >= 0) {
      fractLen = this.columnDefinition.getFields()[parameterIndex].getDecimals();
    } 
    setTimestamp(parameterIndex, x, (Calendar)null, fractLen, targetMysqlType);
  }
  
  public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal, MysqlType targetMysqlType) {
    int fractLen = -1;
    if (!this.session.getServerSession().getCapabilities().serverSupportsFracSecs() || !((Boolean)this.sendFractionalSeconds.getValue()).booleanValue()) {
      fractLen = 0;
    } else if (this.columnDefinition != null && parameterIndex <= (this.columnDefinition.getFields()).length && parameterIndex >= 0 && this.columnDefinition
      .getFields()[parameterIndex].getDecimals() > 0) {
      fractLen = this.columnDefinition.getFields()[parameterIndex].getDecimals();
    } 
    setTimestamp(parameterIndex, x, cal, fractLen, targetMysqlType);
  }
  
  public void setTimestamp(int parameterIndex, Timestamp x, Calendar targetCalendar, int fractionalLength, MysqlType targetMysqlType) {
    if (x == null) {
      setNull(parameterIndex);
      return;
    } 
    if (!this.session.getServerSession().getCapabilities().serverSupportsFracSecs() || !((Boolean)this.sendFractionalSeconds.getValue()).booleanValue())
      x = TimeUtil.truncateFractionalSeconds(x); 
    bindTimestamp(parameterIndex, x, targetCalendar, fractionalLength, targetMysqlType);
  }
  
  public void setObject(int parameterIndex, Object parameterObj) {
    if (parameterObj == null) {
      setNull(parameterIndex);
      return;
    } 
    MysqlType defaultMysqlType = DEFAULT_MYSQL_TYPES.get(parameterObj.getClass());
    if (defaultMysqlType == null) {
      Optional<MysqlType> mysqlType = DEFAULT_MYSQL_TYPES.entrySet().stream().filter(m -> ((Class)m.getKey()).isAssignableFrom(parameterObj.getClass())).map(m -> (MysqlType)m.getValue()).findFirst();
      if (mysqlType.isPresent())
        defaultMysqlType = mysqlType.get(); 
    } 
    if (defaultMysqlType != null) {
      setObject(parameterIndex, parameterObj, defaultMysqlType);
    } else {
      setSerializableObject(parameterIndex, parameterObj);
    } 
  }
  
  public void setObject(int parameterIndex, Object parameterObj, MysqlType targetMysqlType) {
    setObject(parameterIndex, parameterObj, targetMysqlType, (parameterObj instanceof BigDecimal) ? ((BigDecimal)parameterObj).scale() : 0);
  }
  
  public void setObject(int parameterIndex, Object parameterObj, MysqlType targetMysqlType, int scaleOrLength) {
    if (parameterObj == null) {
      setNull(parameterIndex);
      return;
    } 
    try {
      if (parameterObj instanceof LocalDate) {
        switch (targetMysqlType) {
          case DATE:
            setLocalDate(parameterIndex, (LocalDate)parameterObj, targetMysqlType);
            return;
          case DATETIME:
          case TIMESTAMP:
            setLocalDateTime(parameterIndex, LocalDateTime.of((LocalDate)parameterObj, DEFAULT_TIME), targetMysqlType);
            return;
          case YEAR:
            setInt(parameterIndex, ((LocalDate)parameterObj).getYear());
            return;
          case CHAR:
          case VARCHAR:
          case TINYTEXT:
          case TEXT:
          case MEDIUMTEXT:
          case LONGTEXT:
            setString(parameterIndex, parameterObj.toString());
            return;
        } 
        throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
            Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
            .getExceptionInterceptor());
      } 
      if (parameterObj instanceof LocalTime) {
        switch (targetMysqlType) {
          case TIME:
            setLocalTime(parameterIndex, (LocalTime)parameterObj, targetMysqlType);
            return;
          case CHAR:
          case VARCHAR:
          case TINYTEXT:
          case TEXT:
          case MEDIUMTEXT:
          case LONGTEXT:
            setString(parameterIndex, ((LocalTime)parameterObj)
                .format((((Boolean)this.sendFractionalSeconds.getValue()).booleanValue() && ((LocalTime)parameterObj).getNano() > 0) ? TimeUtil.TIME_FORMATTER_WITH_NANOS_NO_OFFSET : TimeUtil.TIME_FORMATTER_NO_FRACT_NO_OFFSET));
            return;
        } 
        throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
            Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
            .getExceptionInterceptor());
      } 
      if (parameterObj instanceof LocalDateTime) {
        switch (targetMysqlType) {
          case DATE:
          case DATETIME:
          case TIMESTAMP:
          case TIME:
            setLocalDateTime(parameterIndex, (LocalDateTime)parameterObj, targetMysqlType);
            return;
          case YEAR:
            setInt(parameterIndex, ((LocalDateTime)parameterObj).getYear());
            return;
          case CHAR:
          case VARCHAR:
          case TINYTEXT:
          case TEXT:
          case MEDIUMTEXT:
          case LONGTEXT:
            setString(parameterIndex, ((LocalDateTime)parameterObj)
                .format((((Boolean)this.sendFractionalSeconds.getValue()).booleanValue() && ((LocalDateTime)parameterObj).getNano() > 0) ? TimeUtil.DATETIME_FORMATTER_WITH_NANOS_NO_OFFSET : TimeUtil.DATETIME_FORMATTER_NO_FRACT_NO_OFFSET));
            return;
        } 
        throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
            Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
            .getExceptionInterceptor());
      } 
      if (parameterObj instanceof OffsetTime) {
        switch (targetMysqlType) {
          case TIME:
            setLocalTime(parameterIndex, ((OffsetTime)parameterObj)
                
                .withOffsetSameInstant(
                  ZoneOffset.ofTotalSeconds(this.session.getServerSession().getDefaultTimeZone().getRawOffset() / 1000))
                .toLocalTime(), targetMysqlType);
            return;
          case CHAR:
          case VARCHAR:
          case TINYTEXT:
          case TEXT:
          case MEDIUMTEXT:
          case LONGTEXT:
            setString(parameterIndex, ((OffsetTime)parameterObj)
                .format((((Boolean)this.sendFractionalSeconds.getValue()).booleanValue() && ((OffsetTime)parameterObj).getNano() > 0) ? TimeUtil.TIME_FORMATTER_WITH_NANOS_WITH_OFFSET : TimeUtil.TIME_FORMATTER_NO_FRACT_WITH_OFFSET));
            return;
        } 
        throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
            Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
            .getExceptionInterceptor());
      } 
      if (parameterObj instanceof OffsetDateTime) {
        Timestamp ts;
        int fractLen;
        switch (targetMysqlType) {
          case DATE:
            setLocalDate(parameterIndex, ((OffsetDateTime)parameterObj)
                .atZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalDate(), targetMysqlType);
            return;
          case DATETIME:
          case TIMESTAMP:
            ts = Timestamp.valueOf(((OffsetDateTime)parameterObj)
                .atZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalDateTime());
            fractLen = -1;
            if (!this.session.getServerSession().getCapabilities().serverSupportsFracSecs() || !((Boolean)this.sendFractionalSeconds.getValue()).booleanValue()) {
              fractLen = 0;
            } else if (this.columnDefinition != null && parameterIndex <= (this.columnDefinition.getFields()).length && parameterIndex >= 0 && this.columnDefinition
              .getFields()[parameterIndex].getDecimals() > 0) {
              fractLen = this.columnDefinition.getFields()[parameterIndex].getDecimals();
            } 
            if (fractLen == 0)
              ts = TimeUtil.truncateFractionalSeconds(ts); 
            bindTimestamp(parameterIndex, ts, null, fractLen, targetMysqlType);
            return;
          case TIME:
            setLocalTime(parameterIndex, ((OffsetDateTime)parameterObj)
                .atZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalTime(), targetMysqlType);
            return;
          case YEAR:
            setInt(parameterIndex, ((OffsetDateTime)parameterObj)
                .atZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).getYear());
            return;
          case CHAR:
          case VARCHAR:
          case TINYTEXT:
          case TEXT:
          case MEDIUMTEXT:
          case LONGTEXT:
            setString(parameterIndex, ((OffsetDateTime)parameterObj)
                .format((((Boolean)this.sendFractionalSeconds.getValue()).booleanValue() && ((OffsetDateTime)parameterObj).getNano() > 0) ? TimeUtil.DATETIME_FORMATTER_WITH_NANOS_WITH_OFFSET : TimeUtil.DATETIME_FORMATTER_NO_FRACT_WITH_OFFSET));
            return;
        } 
        throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
            Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
            .getExceptionInterceptor());
      } 
      if (parameterObj instanceof ZonedDateTime) {
        Timestamp ts;
        int fractLen;
        switch (targetMysqlType) {
          case DATE:
            setLocalDate(parameterIndex, ((ZonedDateTime)parameterObj)
                .withZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalDate(), targetMysqlType);
            return;
          case DATETIME:
          case TIMESTAMP:
            ts = Timestamp.valueOf(((ZonedDateTime)parameterObj)
                .withZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalDateTime());
            fractLen = -1;
            if (!this.session.getServerSession().getCapabilities().serverSupportsFracSecs() || !((Boolean)this.sendFractionalSeconds.getValue()).booleanValue()) {
              fractLen = 0;
            } else if (this.columnDefinition != null && parameterIndex <= (this.columnDefinition.getFields()).length && parameterIndex >= 0 && this.columnDefinition
              .getFields()[parameterIndex].getDecimals() > 0) {
              fractLen = this.columnDefinition.getFields()[parameterIndex].getDecimals();
            } 
            if (fractLen == 0)
              ts = TimeUtil.truncateFractionalSeconds(ts); 
            bindTimestamp(parameterIndex, ts, null, fractLen, targetMysqlType);
            return;
          case TIME:
            setLocalTime(parameterIndex, ((ZonedDateTime)parameterObj)
                .withZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalTime(), targetMysqlType);
            return;
          case YEAR:
            setInt(parameterIndex, ((ZonedDateTime)parameterObj)
                .withZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId()).getYear());
            return;
          case CHAR:
          case VARCHAR:
          case TINYTEXT:
          case TEXT:
          case MEDIUMTEXT:
          case LONGTEXT:
            setString(parameterIndex, ((ZonedDateTime)parameterObj)
                .format((((Boolean)this.sendFractionalSeconds.getValue()).booleanValue() && ((ZonedDateTime)parameterObj).getNano() > 0) ? TimeUtil.DATETIME_FORMATTER_WITH_NANOS_WITH_OFFSET : TimeUtil.DATETIME_FORMATTER_NO_FRACT_WITH_OFFSET));
            return;
        } 
        throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
            Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
            .getExceptionInterceptor());
      } 
      if (parameterObj instanceof Duration) {
        switch (targetMysqlType) {
          case TIME:
            setDuration(parameterIndex, (Duration)parameterObj, targetMysqlType);
            return;
          case CHAR:
          case VARCHAR:
          case TINYTEXT:
          case TEXT:
          case MEDIUMTEXT:
          case LONGTEXT:
            setString(parameterIndex, TimeUtil.getDurationString((Duration)parameterObj));
            return;
        } 
        throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
            Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
            .getExceptionInterceptor());
      } 
      if (parameterObj instanceof Date) {
        Calendar cal;
        switch (targetMysqlType) {
          case DATE:
            setDate(parameterIndex, (Date)parameterObj);
            return;
          case DATETIME:
          case TIMESTAMP:
            setTimestamp(parameterIndex, new Timestamp(((Date)parameterObj).getTime()), targetMysqlType);
            return;
          case YEAR:
            cal = Calendar.getInstance();
            cal.setTime((Date)parameterObj);
            setInt(parameterIndex, cal.get(1));
            return;
          case CHAR:
          case VARCHAR:
          case TINYTEXT:
          case TEXT:
          case MEDIUMTEXT:
          case LONGTEXT:
            setString(parameterIndex, parameterObj.toString());
            return;
        } 
        throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
            Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
            .getExceptionInterceptor());
      } 
      if (parameterObj instanceof Timestamp) {
        Calendar cal;
        String val;
        int dotPos;
        switch (targetMysqlType) {
          case DATE:
            setDate(parameterIndex, new Date(((Date)parameterObj).getTime()));
            return;
          case DATETIME:
          case TIMESTAMP:
            setTimestamp(parameterIndex, (Timestamp)parameterObj, targetMysqlType);
            return;
          case YEAR:
            cal = Calendar.getInstance();
            cal.setTime((Date)parameterObj);
            setInt(parameterIndex, cal.get(1));
            return;
          case TIME:
            setLocalTime(parameterIndex, ((Timestamp)parameterObj).toLocalDateTime().toLocalTime(), targetMysqlType);
            return;
          case CHAR:
          case VARCHAR:
          case TINYTEXT:
          case TEXT:
          case MEDIUMTEXT:
          case LONGTEXT:
            val = parameterObj.toString();
            if ((((Timestamp)parameterObj).getNanos() == 0 || !((Boolean)this.sendFractionalSeconds.getValue()).booleanValue()) && (
              dotPos = val.indexOf(".")) > 0)
              val = val.substring(0, dotPos); 
            setString(parameterIndex, val);
            return;
        } 
        throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
            Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
            .getExceptionInterceptor());
      } 
      if (parameterObj instanceof Time) {
        Timestamp ts;
        int fractLen;
        Calendar cal;
        switch (targetMysqlType) {
          case DATE:
            setDate(parameterIndex, new Date(((Date)parameterObj).getTime()));
            return;
          case DATETIME:
          case TIMESTAMP:
            ts = new Timestamp(((Time)parameterObj).getTime());
            fractLen = -1;
            if (!this.session.getServerSession().getCapabilities().serverSupportsFracSecs() || !((Boolean)this.sendFractionalSecondsForTime.getValue()).booleanValue() || 
              !((Boolean)this.sendFractionalSeconds.getValue()).booleanValue()) {
              fractLen = 0;
            } else if (this.columnDefinition != null && parameterIndex <= (this.columnDefinition.getFields()).length && parameterIndex >= 0 && this.columnDefinition
              .getFields()[parameterIndex].getDecimals() > 0) {
              fractLen = this.columnDefinition.getFields()[parameterIndex].getDecimals();
            } 
            if (fractLen == 0)
              ts = TimeUtil.truncateFractionalSeconds(ts); 
            bindTimestamp(parameterIndex, ts, null, fractLen, MysqlType.DATETIME);
            return;
          case YEAR:
            cal = Calendar.getInstance();
            cal.setTime((Date)parameterObj);
            setInt(parameterIndex, cal.get(1));
            return;
          case TIME:
            setTime(parameterIndex, (Time)parameterObj);
            return;
          case CHAR:
          case VARCHAR:
          case TINYTEXT:
          case TEXT:
          case MEDIUMTEXT:
          case LONGTEXT:
            setString(parameterIndex, 
                
                TimeUtil.getSimpleDateFormat((this.session.getServerSession().getCapabilities().serverSupportsFracSecs() && ((Boolean)this.sendFractionalSeconds
                  .getValue()).booleanValue() && ((Boolean)this.sendFractionalSecondsForTime.getValue()).booleanValue() && 
                  TimeUtil.hasFractionalSeconds((Time)parameterObj).booleanValue()) ? "HH:mm:ss.SSS" : "HH:mm:ss", null)
                .format(parameterObj));
            return;
        } 
        throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
            Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
            .getExceptionInterceptor());
      } 
      if (parameterObj instanceof Date) {
        Calendar cal;
        LocalTime lt;
        if (!((Boolean)this.treatUtilDateAsTimestamp.getValue()).booleanValue()) {
          setSerializableObject(parameterIndex, parameterObj);
          return;
        } 
        switch (targetMysqlType) {
          case DATE:
            setDate(parameterIndex, new Date(((Date)parameterObj).getTime()));
            return;
          case DATETIME:
          case TIMESTAMP:
            setTimestamp(parameterIndex, new Timestamp(((Date)parameterObj).getTime()), targetMysqlType);
            return;
          case YEAR:
            cal = Calendar.getInstance();
            cal.setTime((Date)parameterObj);
            setInt(parameterIndex, cal.get(1));
            return;
          case TIME:
            lt = ((Date)parameterObj).toInstant().atZone(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalTime();
            setLocalTime(parameterIndex, lt, targetMysqlType);
            return;
          case CHAR:
          case VARCHAR:
          case TINYTEXT:
          case TEXT:
          case MEDIUMTEXT:
          case LONGTEXT:
            setString(parameterIndex, TimeUtil.getSimpleDateFormat((this.session
                  .getServerSession().getCapabilities().serverSupportsFracSecs() && ((Boolean)this.sendFractionalSeconds.getValue()).booleanValue() && ((Date)parameterObj)
                  .toInstant().getNano() > 0) ? "yyyy-MM-dd HH:mm:ss.SSS" : "yyyy-MM-dd HH:mm:ss", null)
                .format(parameterObj));
            return;
        } 
        throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
            Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
            .getExceptionInterceptor());
      } 
      if (parameterObj instanceof Calendar) {
        LocalTime lt;
        ZonedDateTime zdt;
        switch (targetMysqlType) {
          case DATE:
            setDate(parameterIndex, new Date(((Calendar)parameterObj).getTimeInMillis()));
            return;
          case DATETIME:
          case TIMESTAMP:
            setTimestamp(parameterIndex, new Timestamp(((Calendar)parameterObj).getTimeInMillis()), targetMysqlType);
            return;
          case YEAR:
            setInt(parameterIndex, ((Calendar)parameterObj).get(1));
            return;
          case TIME:
            lt = ((Calendar)parameterObj).toInstant().atZone(this.session.getServerSession().getDefaultTimeZone().toZoneId()).toLocalTime();
            setLocalTime(parameterIndex, lt, targetMysqlType);
            return;
          case CHAR:
          case VARCHAR:
          case TINYTEXT:
          case TEXT:
          case MEDIUMTEXT:
          case LONGTEXT:
            zdt = ZonedDateTime.ofInstant(((Calendar)parameterObj).toInstant(), ((Calendar)parameterObj).getTimeZone().toZoneId()).withZoneSameInstant(this.session.getServerSession().getDefaultTimeZone().toZoneId());
            setString(parameterIndex, zdt
                .format((zdt.getNano() > 0 && this.session.getServerSession().getCapabilities().serverSupportsFracSecs() && ((Boolean)this.sendFractionalSeconds
                  .getValue()).booleanValue()) ? TimeUtil.DATETIME_FORMATTER_WITH_MILLIS_NO_OFFSET : TimeUtil.DATETIME_FORMATTER_NO_FRACT_NO_OFFSET));
            return;
        } 
        throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
            Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
            .getExceptionInterceptor());
      } 
      if (parameterObj instanceof String) {
        BigDecimal parameterAsNum;
        BigDecimal scaledBigDecimal;
        switch (targetMysqlType) {
          case BOOLEAN:
            if ("true".equalsIgnoreCase((String)parameterObj) || "Y".equalsIgnoreCase((String)parameterObj)) {
              setBoolean(parameterIndex, true);
            } else if ("false".equalsIgnoreCase((String)parameterObj) || "N".equalsIgnoreCase((String)parameterObj)) {
              setBoolean(parameterIndex, false);
            } else if (((String)parameterObj).matches("-?\\d+\\.?\\d*")) {
              setBoolean(parameterIndex, !((String)parameterObj).matches("-?[0]+[.]*[0]*"));
            } else {
              throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
                  Messages.getString("PreparedStatement.66", new Object[] { parameterObj }), this.session.getExceptionInterceptor());
            } 
            return;
          case BIT:
            if ("1".equals(parameterObj) || "0".equals(parameterObj)) {
              setInt(parameterIndex, Integer.valueOf((String)parameterObj).intValue());
            } else {
              boolean parameterAsBoolean = "true".equalsIgnoreCase((String)parameterObj);
              setInt(parameterIndex, parameterAsBoolean ? 1 : 0);
            } 
            return;
          case TINYINT:
          case TINYINT_UNSIGNED:
          case SMALLINT:
          case SMALLINT_UNSIGNED:
          case MEDIUMINT:
          case MEDIUMINT_UNSIGNED:
          case INT:
          case INT_UNSIGNED:
            setInt(parameterIndex, Integer.valueOf((String)parameterObj).intValue());
            return;
          case BIGINT:
            setLong(parameterIndex, Long.valueOf((String)parameterObj).longValue());
            return;
          case BIGINT_UNSIGNED:
            setLong(parameterIndex, (new BigInteger((String)parameterObj)).longValue());
            return;
          case FLOAT:
          case FLOAT_UNSIGNED:
            setFloat(parameterIndex, Float.valueOf((String)parameterObj).floatValue());
            return;
          case DOUBLE:
          case DOUBLE_UNSIGNED:
            setDouble(parameterIndex, Double.valueOf((String)parameterObj).doubleValue());
            return;
          case DECIMAL:
          case DECIMAL_UNSIGNED:
            parameterAsNum = new BigDecimal((String)parameterObj);
            scaledBigDecimal = null;
            try {
              scaledBigDecimal = parameterAsNum.setScale(scaleOrLength);
            } catch (ArithmeticException ex) {
              try {
                scaledBigDecimal = parameterAsNum.setScale(scaleOrLength, 4);
              } catch (ArithmeticException arEx) {
                throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
                    Messages.getString("PreparedStatement.65", new Object[] { Integer.valueOf(scaleOrLength), parameterAsNum }), this.session
                    .getExceptionInterceptor());
              } 
            } 
            setBigDecimal(parameterIndex, scaledBigDecimal);
            return;
          case CHAR:
          case VARCHAR:
          case TINYTEXT:
          case TEXT:
          case MEDIUMTEXT:
          case LONGTEXT:
          case ENUM:
          case SET:
          case JSON:
            setString(parameterIndex, parameterObj.toString());
            return;
          case BINARY:
          case GEOMETRY:
          case VARBINARY:
          case TINYBLOB:
          case BLOB:
          case MEDIUMBLOB:
          case LONGBLOB:
            setBytes(parameterIndex, StringUtils.getBytes(parameterObj.toString(), this.charEncoding));
            return;
          case DATE:
          case DATETIME:
          case TIMESTAMP:
          case YEAR:
          case TIME:
            setObject(parameterIndex, TimeUtil.parseToDateTimeObject((String)parameterObj, targetMysqlType), targetMysqlType);
            return;
          case UNKNOWN:
            setSerializableObject(parameterIndex, parameterObj);
            return;
        } 
        throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
            Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
            .getExceptionInterceptor());
      } 
      if (parameterObj instanceof InputStream) {
        setBinaryStream(parameterIndex, (InputStream)parameterObj, -1);
      } else {
        if (parameterObj instanceof Boolean) {
          switch (targetMysqlType) {
            case BOOLEAN:
              setBoolean(parameterIndex, ((Boolean)parameterObj).booleanValue());
              return;
            case YEAR:
            case BIT:
            case TINYINT:
            case TINYINT_UNSIGNED:
            case SMALLINT:
            case SMALLINT_UNSIGNED:
            case MEDIUMINT:
            case MEDIUMINT_UNSIGNED:
            case INT:
            case INT_UNSIGNED:
              setInt(parameterIndex, ((Boolean)parameterObj).booleanValue() ? 1 : 0);
              return;
            case BIGINT:
            case BIGINT_UNSIGNED:
              setLong(parameterIndex, ((Boolean)parameterObj).booleanValue() ? 1L : 0L);
              return;
            case FLOAT:
            case FLOAT_UNSIGNED:
              setFloat(parameterIndex, ((Boolean)parameterObj).booleanValue() ? 1.0F : 0.0F);
              return;
            case DOUBLE:
            case DOUBLE_UNSIGNED:
              setDouble(parameterIndex, ((Boolean)parameterObj).booleanValue() ? 1.0D : 0.0D);
              return;
            case DECIMAL:
            case DECIMAL_UNSIGNED:
              setBigDecimal(parameterIndex, new BigDecimal(((Boolean)parameterObj).booleanValue() ? 1.0D : 0.0D));
              return;
            case CHAR:
            case VARCHAR:
            case TINYTEXT:
            case TEXT:
            case MEDIUMTEXT:
            case LONGTEXT:
              setString(parameterIndex, parameterObj.toString());
              return;
          } 
          throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
              Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
              .getExceptionInterceptor());
        } 
        if (parameterObj instanceof Number) {
          Number parameterAsNum = (Number)parameterObj;
          switch (targetMysqlType) {
            case BOOLEAN:
              setBoolean(parameterIndex, (parameterAsNum.intValue() != 0));
              return;
            case YEAR:
            case BIT:
            case TINYINT:
            case TINYINT_UNSIGNED:
            case SMALLINT:
            case SMALLINT_UNSIGNED:
            case MEDIUMINT:
            case MEDIUMINT_UNSIGNED:
            case INT:
            case INT_UNSIGNED:
              setInt(parameterIndex, parameterAsNum.intValue());
              return;
            case BIGINT:
            case BIGINT_UNSIGNED:
              setLong(parameterIndex, parameterAsNum.longValue());
              return;
            case FLOAT:
            case FLOAT_UNSIGNED:
              setFloat(parameterIndex, parameterAsNum.floatValue());
              return;
            case DOUBLE:
            case DOUBLE_UNSIGNED:
              setDouble(parameterIndex, parameterAsNum.doubleValue());
              return;
            case DECIMAL:
            case DECIMAL_UNSIGNED:
              if (parameterAsNum instanceof BigDecimal) {
                BigDecimal scaledBigDecimal = null;
                try {
                  scaledBigDecimal = ((BigDecimal)parameterAsNum).setScale(scaleOrLength);
                } catch (ArithmeticException ex) {
                  try {
                    scaledBigDecimal = ((BigDecimal)parameterAsNum).setScale(scaleOrLength, 4);
                  } catch (ArithmeticException arEx) {
                    throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
                        Messages.getString("PreparedStatement.65", new Object[] { Integer.valueOf(scaleOrLength), parameterAsNum }), this.session
                        .getExceptionInterceptor());
                  } 
                } 
                setBigDecimal(parameterIndex, scaledBigDecimal);
              } else if (parameterAsNum instanceof BigInteger) {
                setBigDecimal(parameterIndex, new BigDecimal((BigInteger)parameterAsNum, scaleOrLength));
              } else {
                setBigDecimal(parameterIndex, new BigDecimal(parameterAsNum.doubleValue()));
              } 
              return;
            case CHAR:
            case VARCHAR:
            case TINYTEXT:
            case TEXT:
            case MEDIUMTEXT:
            case LONGTEXT:
            case ENUM:
            case SET:
            case JSON:
              if (parameterObj instanceof BigDecimal) {
                setString(parameterIndex, StringUtils.fixDecimalExponent(((BigDecimal)parameterObj).toPlainString()));
              } else {
                setString(parameterIndex, parameterObj.toString());
              } 
              return;
            case BINARY:
            case GEOMETRY:
            case VARBINARY:
            case TINYBLOB:
            case BLOB:
            case MEDIUMBLOB:
            case LONGBLOB:
              setBytes(parameterIndex, StringUtils.getBytes(parameterObj.toString(), this.charEncoding));
              return;
          } 
          throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
              Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
              .getExceptionInterceptor());
        } 
        switch (targetMysqlType) {
          case BOOLEAN:
            throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
                Messages.getString("PreparedStatement.66", new Object[] { parameterObj.getClass().getName() }), this.session
                .getExceptionInterceptor());
          case CHAR:
          case VARCHAR:
          case TINYTEXT:
          case TEXT:
          case MEDIUMTEXT:
          case LONGTEXT:
          case ENUM:
          case SET:
          case JSON:
            if (parameterObj instanceof BigDecimal) {
              setString(parameterIndex, StringUtils.fixDecimalExponent(((BigDecimal)parameterObj).toPlainString()));
            } else if (parameterObj instanceof Clob) {
              setClob(parameterIndex, (Clob)parameterObj);
            } else {
              setString(parameterIndex, parameterObj.toString());
            } 
            return;
          case BINARY:
          case GEOMETRY:
          case VARBINARY:
          case TINYBLOB:
          case BLOB:
          case MEDIUMBLOB:
          case LONGBLOB:
            if (parameterObj instanceof byte[]) {
              setBytes(parameterIndex, (byte[])parameterObj);
            } else if (parameterObj instanceof Blob) {
              setBlob(parameterIndex, (Blob)parameterObj);
            } else {
              setBytes(parameterIndex, StringUtils.getBytes(parameterObj.toString(), this.charEncoding));
            } 
            return;
          case UNKNOWN:
            setSerializableObject(parameterIndex, parameterObj);
            return;
        } 
        throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, 
            Messages.getString("PreparedStatement.67", new Object[] { parameterObj.getClass().getName(), targetMysqlType.toString() }), this.session
            .getExceptionInterceptor());
      } 
    } catch (Exception ex) {
      throw ExceptionFactory.createException(
          Messages.getString("PreparedStatement.17") + parameterObj.getClass().toString() + Messages.getString("PreparedStatement.18") + ex
          .getClass().getName() + Messages.getString("PreparedStatement.19") + ex.getMessage(), ex, this.session
          .getExceptionInterceptor());
    } 
  }
  
  protected final void setSerializableObject(int parameterIndex, Object parameterObj) {
    try {
      ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
      ObjectOutputStream objectOut = new ObjectOutputStream(bytesOut);
      objectOut.writeObject(parameterObj);
      objectOut.flush();
      objectOut.close();
      bytesOut.flush();
      bytesOut.close();
      byte[] buf = bytesOut.toByteArray();
      ByteArrayInputStream bytesIn = new ByteArrayInputStream(buf);
      setBinaryStream(parameterIndex, bytesIn, buf.length);
      this.bindValues[parameterIndex].setMysqlType(MysqlType.BINARY);
    } catch (Exception ex) {
      throw (WrongArgumentException)ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.54") + ex.getClass().getName(), ex, this.session
          .getExceptionInterceptor());
    } 
  }
  
  public boolean isNull(int parameterIndex) {
    return this.bindValues[parameterIndex].isNull();
  }
  
  public byte[] getBytesRepresentation(int parameterIndex) {
    if (this.bindValues[parameterIndex].isStream())
      return streamToBytes(parameterIndex, ((Boolean)this.session.getPropertySet().getBooleanProperty(PropertyKey.useStreamLengthsInPrepStmts).getValue()).booleanValue()); 
    byte[] parameterVal = this.bindValues[parameterIndex].getByteValue();
    if (parameterVal == null)
      return null; 
    return StringUtils.unquoteBytes(parameterVal);
  }
  
  public AbstractQueryBindings(int parameterCount, Session sess) {
    this.streamConvertBuf = null;
    this.session = sess;
    this.charEncoding = (String)this.session.getPropertySet().getStringProperty(PropertyKey.characterEncoding).getValue();
    this.preserveInstants = this.session.getPropertySet().getBooleanProperty(PropertyKey.preserveInstants);
    this.sendFractionalSeconds = this.session.getPropertySet().getBooleanProperty(PropertyKey.sendFractionalSeconds);
    this.sendFractionalSecondsForTime = this.session.getPropertySet().getBooleanProperty(PropertyKey.sendFractionalSecondsForTime);
    this.treatUtilDateAsTimestamp = this.session.getPropertySet().getBooleanProperty(PropertyKey.treatUtilDateAsTimestamp);
    this.useStreamLengthsInPrepStmts = this.session.getPropertySet().getBooleanProperty(PropertyKey.useStreamLengthsInPrepStmts);
    initBindValues(parameterCount);
  }
  
  private final byte[] streamToBytes(int parameterIndex, boolean useLength) {
    InputStream in = this.bindValues[parameterIndex].getStreamValue();
    in.mark(2147483647);
    try {
      if (this.streamConvertBuf == null)
        this.streamConvertBuf = new byte[4096]; 
      if (this.bindValues[parameterIndex].getStreamLength() == -1L)
        useLength = false; 
      ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
      int bc = useLength ? Util.readBlock(in, this.streamConvertBuf, (int)this.bindValues[parameterIndex].getStreamLength(), null) : Util.readBlock(in, this.streamConvertBuf, null);
      int lengthLeftToRead = (int)this.bindValues[parameterIndex].getStreamLength() - bc;
      while (bc > 0) {
        bytesOut.write(this.streamConvertBuf, 0, bc);
        if (useLength) {
          bc = Util.readBlock(in, this.streamConvertBuf, lengthLeftToRead, null);
          if (bc > 0)
            lengthLeftToRead -= bc; 
          continue;
        } 
        bc = Util.readBlock(in, this.streamConvertBuf, null);
      } 
      return bytesOut.toByteArray();
    } finally {
      try {
        in.reset();
      } catch (IOException iOException) {}
      if (((Boolean)this.session.getPropertySet().getBooleanProperty(PropertyKey.autoClosePStmtStreams).getValue()).booleanValue()) {
        try {
          in.close();
        } catch (IOException iOException) {}
        in = null;
      } 
    } 
  }
  
  protected abstract void initBindValues(int paramInt);
  
  public abstract AbstractQueryBindings<T> clone();
  
  public abstract void checkParameterSet(int paramInt);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\AbstractQueryBindings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
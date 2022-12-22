package com.mysql.cj;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NativeQueryAttributesBindValue implements QueryAttributesBindValue {
  private static final Map<Class<?>, Integer> JAVA_TO_MYSQL_FIELD_TYPE = new HashMap<>();
  
  private String name;
  
  public Object value;
  
  static {
    JAVA_TO_MYSQL_FIELD_TYPE.put(String.class, Integer.valueOf(254));
    JAVA_TO_MYSQL_FIELD_TYPE.put(Boolean.class, Integer.valueOf(1));
    JAVA_TO_MYSQL_FIELD_TYPE.put(Byte.class, Integer.valueOf(1));
    JAVA_TO_MYSQL_FIELD_TYPE.put(Short.class, Integer.valueOf(2));
    JAVA_TO_MYSQL_FIELD_TYPE.put(Integer.class, Integer.valueOf(3));
    JAVA_TO_MYSQL_FIELD_TYPE.put(Long.class, Integer.valueOf(8));
    JAVA_TO_MYSQL_FIELD_TYPE.put(BigInteger.class, Integer.valueOf(8));
    JAVA_TO_MYSQL_FIELD_TYPE.put(Float.class, Integer.valueOf(4));
    JAVA_TO_MYSQL_FIELD_TYPE.put(Double.class, Integer.valueOf(5));
    JAVA_TO_MYSQL_FIELD_TYPE.put(BigDecimal.class, Integer.valueOf(5));
    JAVA_TO_MYSQL_FIELD_TYPE.put(Date.class, Integer.valueOf(10));
    JAVA_TO_MYSQL_FIELD_TYPE.put(LocalDate.class, Integer.valueOf(10));
    JAVA_TO_MYSQL_FIELD_TYPE.put(Time.class, Integer.valueOf(11));
    JAVA_TO_MYSQL_FIELD_TYPE.put(LocalTime.class, Integer.valueOf(11));
    JAVA_TO_MYSQL_FIELD_TYPE.put(OffsetTime.class, Integer.valueOf(11));
    JAVA_TO_MYSQL_FIELD_TYPE.put(Duration.class, Integer.valueOf(11));
    JAVA_TO_MYSQL_FIELD_TYPE.put(LocalDateTime.class, Integer.valueOf(12));
    JAVA_TO_MYSQL_FIELD_TYPE.put(Timestamp.class, Integer.valueOf(7));
    JAVA_TO_MYSQL_FIELD_TYPE.put(Instant.class, Integer.valueOf(7));
    JAVA_TO_MYSQL_FIELD_TYPE.put(OffsetDateTime.class, Integer.valueOf(7));
    JAVA_TO_MYSQL_FIELD_TYPE.put(ZonedDateTime.class, Integer.valueOf(7));
    JAVA_TO_MYSQL_FIELD_TYPE.put(Date.class, Integer.valueOf(7));
    JAVA_TO_MYSQL_FIELD_TYPE.put(Calendar.class, Integer.valueOf(7));
  }
  
  protected int type = 6;
  
  protected NativeQueryAttributesBindValue(String name, Object value) {
    this.name = name;
    this.value = value;
    this.type = getMysqlFieldType(value);
  }
  
  private int getMysqlFieldType(Object obj) {
    if (obj == null)
      return 6; 
    Integer mysqlFieldType = JAVA_TO_MYSQL_FIELD_TYPE.get(obj.getClass());
    if (mysqlFieldType != null)
      return mysqlFieldType.intValue(); 
    Optional<Integer> mysqlType = JAVA_TO_MYSQL_FIELD_TYPE.entrySet().stream().filter(m -> ((Class)m.getKey()).isAssignableFrom(obj.getClass())).map(m -> (Integer)m.getValue()).findFirst();
    if (mysqlType.isPresent())
      return ((Integer)mysqlType.get()).intValue(); 
    return 254;
  }
  
  public boolean isNull() {
    return (this.type == 6);
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getType() {
    return this.type;
  }
  
  public Object getValue() {
    return this.value;
  }
  
  public long getBoundLength() {
    if (isNull())
      return 0L; 
    switch (this.type) {
      case 1:
        return 1L;
      case 2:
        return 2L;
      case 3:
        return 4L;
      case 8:
        return 8L;
      case 4:
        return 4L;
      case 5:
        return 8L;
      case 10:
        return 5L;
      case 11:
        return 13L;
      case 12:
        return 12L;
      case 7:
        return 14L;
      case 254:
        return (this.value.toString().length() + 9);
    } 
    return 0L;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\NativeQueryAttributesBindValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
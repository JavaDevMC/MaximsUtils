package com.mysql.cj.protocol.a;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.DataReadException;
import com.mysql.cj.exceptions.NumberOutOfRange;
import com.mysql.cj.protocol.InternalDate;
import com.mysql.cj.protocol.InternalTime;
import com.mysql.cj.protocol.InternalTimestamp;
import com.mysql.cj.protocol.ValueDecoder;
import com.mysql.cj.result.Field;
import com.mysql.cj.result.ValueFactory;
import com.mysql.cj.util.StringUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MysqlTextValueDecoder implements ValueDecoder {
  public static final int DATE_BUF_LEN = 10;
  
  public static final int TIME_STR_LEN_MIN = 8;
  
  public static final int TIME_STR_LEN_MAX_NO_FRAC = 10;
  
  public static final int TIME_STR_LEN_MAX_WITH_MICROS = 17;
  
  public static final int TIMESTAMP_STR_LEN_NO_FRAC = 19;
  
  public static final int TIMESTAMP_STR_LEN_WITH_MICROS = 26;
  
  public static final int TIMESTAMP_STR_LEN_WITH_NANOS = 29;
  
  public static final Pattern TIME_PTRN = Pattern.compile("[-]{0,1}\\d{2,3}:\\d{2}:\\d{2}(\\.\\d{1,9})?");
  
  public static final int MAX_SIGNED_LONG_LEN = 20;
  
  public <T> T decodeDate(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    return (T)vf.createFromDate(getDate(bytes, offset, length));
  }
  
  public <T> T decodeTime(byte[] bytes, int offset, int length, int scale, ValueFactory<T> vf) {
    return (T)vf.createFromTime(getTime(bytes, offset, length, scale));
  }
  
  public <T> T decodeTimestamp(byte[] bytes, int offset, int length, int scale, ValueFactory<T> vf) {
    return (T)vf.createFromTimestamp(getTimestamp(bytes, offset, length, scale));
  }
  
  public <T> T decodeDatetime(byte[] bytes, int offset, int length, int scale, ValueFactory<T> vf) {
    return (T)vf.createFromDatetime(getTimestamp(bytes, offset, length, scale));
  }
  
  public <T> T decodeUInt1(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    return (T)vf.createFromLong(getInt(bytes, offset, offset + length));
  }
  
  public <T> T decodeInt1(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    return (T)vf.createFromLong(getInt(bytes, offset, offset + length));
  }
  
  public <T> T decodeUInt2(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    return (T)vf.createFromLong(getInt(bytes, offset, offset + length));
  }
  
  public <T> T decodeInt2(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    return (T)vf.createFromLong(getInt(bytes, offset, offset + length));
  }
  
  public <T> T decodeUInt4(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    return (T)vf.createFromLong(getLong(bytes, offset, offset + length));
  }
  
  public <T> T decodeInt4(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    return (T)vf.createFromLong(getInt(bytes, offset, offset + length));
  }
  
  public <T> T decodeUInt8(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    if (length <= 19 && bytes[offset] >= 48 && bytes[offset] <= 56)
      return decodeInt8(bytes, offset, length, vf); 
    return (T)vf.createFromBigInteger(getBigInteger(bytes, offset, length));
  }
  
  public <T> T decodeInt8(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    return (T)vf.createFromLong(getLong(bytes, offset, offset + length));
  }
  
  public <T> T decodeFloat(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    return decodeDouble(bytes, offset, length, vf);
  }
  
  public <T> T decodeDouble(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    return (T)vf.createFromDouble(getDouble(bytes, offset, length).doubleValue());
  }
  
  public <T> T decodeDecimal(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    BigDecimal d = new BigDecimal(StringUtils.toAsciiString(bytes, offset, length));
    return (T)vf.createFromBigDecimal(d);
  }
  
  public <T> T decodeByteArray(byte[] bytes, int offset, int length, Field f, ValueFactory<T> vf) {
    return (T)vf.createFromBytes(bytes, offset, length, f);
  }
  
  public <T> T decodeBit(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    return (T)vf.createFromBit(bytes, offset, length);
  }
  
  public <T> T decodeSet(byte[] bytes, int offset, int length, Field f, ValueFactory<T> vf) {
    return decodeByteArray(bytes, offset, length, f, vf);
  }
  
  public <T> T decodeYear(byte[] bytes, int offset, int length, ValueFactory<T> vf) {
    return (T)vf.createFromYear(getLong(bytes, offset, offset + length));
  }
  
  public static int getInt(byte[] buf, int offset, int endpos) throws NumberFormatException {
    long l = getLong(buf, offset, endpos);
    if (l < -2147483648L || l > 2147483647L)
      throw new NumberOutOfRange(Messages.getString("StringUtils.badIntFormat", new Object[] { StringUtils.toString(buf, offset, endpos - offset) })); 
    return (int)l;
  }
  
  public static long getLong(byte[] buf, int offset, int endpos) throws NumberFormatException {
    int base = 10;
    int s = offset;
    while (s < endpos && Character.isWhitespace((char)buf[s]))
      s++; 
    if (s == endpos)
      throw new NumberFormatException(StringUtils.toString(buf)); 
    boolean negative = false;
    if ((char)buf[s] == '-') {
      negative = true;
      s++;
    } else if ((char)buf[s] == '+') {
      s++;
    } 
    int save = s;
    long cutoff = Long.MAX_VALUE / base;
    long cutlim = (int)(Long.MAX_VALUE % base);
    if (negative)
      cutlim++; 
    boolean overflow = false;
    long i = 0L;
    for (; s < endpos; s++) {
      char c = (char)buf[s];
      if (c >= '0' && c <= '9') {
        c = (char)(c - 48);
      } else if (Character.isLetter(c)) {
        c = (char)(Character.toUpperCase(c) - 65 + 10);
      } else {
        break;
      } 
      if (c >= base)
        break; 
      if (i > cutoff || (i == cutoff && c > cutlim)) {
        overflow = true;
      } else {
        i *= base;
        i += c;
      } 
    } 
    if (s == save)
      throw new NumberFormatException(
          Messages.getString("StringUtils.badIntFormat", new Object[] { StringUtils.toString(buf, offset, endpos - offset) })); 
    if (overflow)
      throw new NumberOutOfRange(Messages.getString("StringUtils.badIntFormat", new Object[] { StringUtils.toString(buf, offset, endpos - offset) })); 
    return negative ? -i : i;
  }
  
  public static BigInteger getBigInteger(byte[] buf, int offset, int length) throws NumberFormatException {
    BigInteger i = new BigInteger(StringUtils.toAsciiString(buf, offset, length));
    return i;
  }
  
  public static Double getDouble(byte[] bytes, int offset, int length) {
    return Double.valueOf(Double.parseDouble(StringUtils.toAsciiString(bytes, offset, length)));
  }
  
  public static boolean isDate(String s) {
    return (s.length() == 10 && s.charAt(4) == '-' && s.charAt(7) == '-');
  }
  
  public static boolean isTime(String s) {
    Matcher matcher = TIME_PTRN.matcher(s);
    return matcher.matches();
  }
  
  public static boolean isTimestamp(String s) {
    Pattern DATETIME_PTRN = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}(\\.\\d{1,9}){0,1}");
    Matcher matcher = DATETIME_PTRN.matcher(s);
    return matcher.matches();
  }
  
  public static InternalDate getDate(byte[] bytes, int offset, int length) {
    if (length != 10)
      throw new DataReadException(Messages.getString("ResultSet.InvalidLengthForType", new Object[] { Integer.valueOf(length), "DATE" })); 
    int year = getInt(bytes, offset, offset + 4);
    int month = getInt(bytes, offset + 5, offset + 7);
    int day = getInt(bytes, offset + 8, offset + 10);
    return new InternalDate(year, month, day);
  }
  
  public static InternalTime getTime(byte[] bytes, int offset, int length, int scale) {
    int pos = 0;
    if (length < 8 || length > 17)
      throw new DataReadException(Messages.getString("ResultSet.InvalidLengthForType", new Object[] { Integer.valueOf(length), "TIME" })); 
    boolean negative = false;
    if (bytes[offset] == 45) {
      pos++;
      negative = true;
    } 
    int segmentLen;
    for (segmentLen = 0; Character.isDigit((char)bytes[offset + pos + segmentLen]); segmentLen++);
    if (segmentLen == 0 || bytes[offset + pos + segmentLen] != 58)
      throw new DataReadException(
          Messages.getString("ResultSet.InvalidFormatForType", new Object[] { "TIME", StringUtils.toString(bytes, offset, length) })); 
    int hours = getInt(bytes, offset + pos, offset + pos + segmentLen);
    if (negative)
      hours *= -1; 
    pos += segmentLen + 1;
    for (segmentLen = 0; Character.isDigit((char)bytes[offset + pos + segmentLen]); segmentLen++);
    if (segmentLen != 2 || bytes[offset + pos + segmentLen] != 58)
      throw new DataReadException(
          Messages.getString("ResultSet.InvalidFormatForType", new Object[] { "TIME", StringUtils.toString(bytes, offset, length) })); 
    int minutes = getInt(bytes, offset + pos, offset + pos + segmentLen);
    pos += segmentLen + 1;
    for (segmentLen = 0; offset + pos + segmentLen < offset + length && Character.isDigit((char)bytes[offset + pos + segmentLen]); segmentLen++);
    if (segmentLen != 2)
      throw new DataReadException(
          Messages.getString("ResultSet.InvalidFormatForType", new Object[] { StringUtils.toString(bytes, offset, length), "TIME" })); 
    int seconds = getInt(bytes, offset + pos, offset + pos + segmentLen);
    pos += segmentLen;
    int nanos = 0;
    if (length > pos) {
      pos++;
      for (segmentLen = 0; offset + pos + segmentLen < offset + length && Character.isDigit((char)bytes[offset + pos + segmentLen]); segmentLen++);
      if (segmentLen + pos != length)
        throw new DataReadException(
            Messages.getString("ResultSet.InvalidFormatForType", new Object[] { StringUtils.toString(bytes, offset, length), "TIME" })); 
      nanos = getInt(bytes, offset + pos, offset + pos + segmentLen);
      nanos *= (int)Math.pow(10.0D, (9 - segmentLen));
    } 
    return new InternalTime(hours, minutes, seconds, nanos, scale);
  }
  
  public static InternalTimestamp getTimestamp(byte[] bytes, int offset, int length, int scale) {
    int nanos;
    if (length < 19 || (length > 26 && length != 29))
      throw new DataReadException(Messages.getString("ResultSet.InvalidLengthForType", new Object[] { Integer.valueOf(length), "TIMESTAMP" })); 
    if (length != 19)
      if (bytes[offset + 19] != 46 || length < 21)
        throw new DataReadException(
            Messages.getString("ResultSet.InvalidFormatForType", new Object[] { StringUtils.toString(bytes, offset, length), "TIMESTAMP" }));  
    if (bytes[offset + 4] != 45 || bytes[offset + 7] != 45 || bytes[offset + 10] != 32 || bytes[offset + 13] != 58 || bytes[offset + 16] != 58)
      throw new DataReadException(
          Messages.getString("ResultSet.InvalidFormatForType", new Object[] { StringUtils.toString(bytes, offset, length), "TIMESTAMP" })); 
    int year = getInt(bytes, offset, offset + 4);
    int month = getInt(bytes, offset + 5, offset + 7);
    int day = getInt(bytes, offset + 8, offset + 10);
    int hours = getInt(bytes, offset + 11, offset + 13);
    int minutes = getInt(bytes, offset + 14, offset + 16);
    int seconds = getInt(bytes, offset + 17, offset + 19);
    if (length == 29) {
      nanos = getInt(bytes, offset + 20, offset + length);
    } else {
      nanos = (length == 19) ? 0 : getInt(bytes, offset + 20, offset + length);
      nanos *= (int)Math.pow(10.0D, (9 - length - 19 - 1));
    } 
    return new InternalTimestamp(year, month, day, hours, minutes, seconds, nanos, scale);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\a\MysqlTextValueDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
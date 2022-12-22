package com.mysql.cj.xdevapi;

import java.util.List;

public interface RowResult extends FetchResult<Row>, Result {
  int getColumnCount();
  
  List<Column> getColumns();
  
  List<String> getColumnNames();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\RowResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.mysql.cj.protocol;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.Query;
import com.mysql.cj.Session;

public interface ResultsetRowsOwner {
  void closeOwner(boolean paramBoolean);
  
  MysqlConnection getConnection();
  
  Session getSession();
  
  Object getSyncMutex();
  
  String getPointOfOrigin();
  
  int getOwnerFetchSize();
  
  Query getOwningQuery();
  
  int getOwningStatementMaxRows();
  
  int getOwningStatementFetchSize();
  
  long getOwningStatementServerId();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\ResultsetRowsOwner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
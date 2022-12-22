package com.mysql.cj;

public interface CancelQueryTask {
  boolean cancel();
  
  Throwable getCaughtWhileCancelling();
  
  void setCaughtWhileCancelling(Throwable paramThrowable);
  
  Query getQueryToCancel();
  
  void setQueryToCancel(Query paramQuery);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\CancelQueryTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
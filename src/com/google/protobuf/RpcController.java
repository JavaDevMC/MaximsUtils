package com.google.protobuf;

public interface RpcController {
  void reset();
  
  boolean failed();
  
  String errorText();
  
  void startCancel();
  
  void setFailed(String paramString);
  
  boolean isCanceled();
  
  void notifyOnCancel(RpcCallback<Object> paramRpcCallback);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\RpcController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
package com.google.protobuf;

public interface BlockingService {
  Descriptors.ServiceDescriptor getDescriptorForType();
  
  Message callBlockingMethod(Descriptors.MethodDescriptor paramMethodDescriptor, RpcController paramRpcController, Message paramMessage) throws ServiceException;
  
  Message getRequestPrototype(Descriptors.MethodDescriptor paramMethodDescriptor);
  
  Message getResponsePrototype(Descriptors.MethodDescriptor paramMethodDescriptor);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\BlockingService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
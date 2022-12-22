package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMultimap;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
interface ServiceManagerBridge {
  ImmutableMultimap<Service.State, Service> servicesByState();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\ServiceManagerBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
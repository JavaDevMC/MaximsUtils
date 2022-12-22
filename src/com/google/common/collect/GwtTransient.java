package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@ElementTypesAreNonnullByDefault
@GwtCompatible
@interface GwtTransient {}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\GwtTransient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.gogent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Example {
    /**
     * 命令
     * @return
     */
    String command();

    /**
     * 相应的class
     * @return
     */
    Class<?> ref();

    /**
     * 描述
     * @return
     */
    String description() default "";
}

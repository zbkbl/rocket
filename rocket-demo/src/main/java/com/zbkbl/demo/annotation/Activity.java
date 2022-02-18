package com.zbkbl.demo.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RepeatActivity.class)
public @interface Activity {

    String name() default "";

    String activityEnum() default "";

    String logEventEnum() default "";

    String userId() default "";

    String bizId() default "";

}

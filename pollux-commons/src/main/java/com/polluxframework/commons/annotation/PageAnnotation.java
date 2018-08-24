package com.polluxframework.commons.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author zhumin0508
 * created in  2018/5/11 10:55
 * modified By:
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface PageAnnotation {
}

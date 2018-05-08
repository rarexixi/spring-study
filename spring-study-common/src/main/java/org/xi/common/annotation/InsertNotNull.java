package org.xi.common.annotation;

/**
 * 
 * All Rights Reserved.
 * @version 1.0 2018/05/08 13:15 郗世豪（xish@cloud-young.com）
 */
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD})
@java.lang.annotation.Documented
public @interface InsertNotNull {
    String name() default "";
    boolean required() default true;
}

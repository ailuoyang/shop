package com.zqsweb.zqscommon.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/*
 *   @author zhangqisheng
 *   @date 2020-05-06 13:33
 *   @description 没用的,就是方便用来跳转到指定的类
 */
@Target({ElementType.TYPE,
        ElementType.FIELD,
        ElementType.METHOD,
        ElementType.PARAMETER,
        ElementType.CONSTRUCTOR,
        ElementType.LOCAL_VARIABLE,
        ElementType.ANNOTATION_TYPE,
        ElementType.PACKAGE,
        ElementType.TYPE_PARAMETER,
        ElementType.TYPE_USE })
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Clazz {
    Class clazz();
}

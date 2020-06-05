package com.zqsweb.zqscommon.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import androidx.annotation.LayoutRes;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 *   @author zhangqisheng
 *   @date 2020-05-10 16:56
 *   @description
 */
@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Documented
public @interface LayoutId {
    @LayoutRes int value();
}

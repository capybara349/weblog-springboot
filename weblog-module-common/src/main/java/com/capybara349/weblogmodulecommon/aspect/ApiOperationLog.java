package com.capybara349.weblogmodulecommon.aspect;

import java.lang.annotation.*;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.12 01:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {
    /**
     * API 功能描述
     *
     * @return String
     */
    String description() default "";
}

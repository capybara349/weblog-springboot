package com.capybara349.weblog.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 在非 Spring 管理的类中，获取 Spring 容器中的 Bean
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 15:05
 */
@Component
public class SpringContext {

    private static ApplicationContext context;

    @Autowired
    public SpringContext(ApplicationContext applicationContext) {
        SpringContext.context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}

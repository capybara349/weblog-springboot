package com.capybara349.weblog.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.13 10:40
 */
@Configuration
@MapperScan("com.capybara349.weblogmodulecommon.domain.mapper")
public class MybatisPlusConfig {
}

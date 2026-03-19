package com.capybara349.weblog.common.constant;

import java.time.format.DateTimeFormatter;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.18 15:07
 */
public interface Constants {
    /**
     * 月-日 格式
     */
    DateTimeFormatter MONTH_DAY_FORMATTER = DateTimeFormatter.ofPattern("MM-dd");
    /**
     * 年-月-日 小时-分钟-秒
     */
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

}
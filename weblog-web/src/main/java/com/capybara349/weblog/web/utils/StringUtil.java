package com.capybara349.weblog.web.utils;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.20 16:34
 */
public class StringUtil {

    /**
     * 判断字符串是否是纯数字
     * @param str
     * @return
     */
    public static boolean isPureNumber(String str) {
        return str.matches("\\d+");
    }

}

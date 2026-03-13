package com.capybara349.weblog.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.12 01:38
 */
@Slf4j
public class JsonUtil {

    public static final ObjectMapper INSTANCE = new ObjectMapper();

    public static String toJsonString(Object object) {
        try {
            return INSTANCE.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return object.toString();
        }
    }
}

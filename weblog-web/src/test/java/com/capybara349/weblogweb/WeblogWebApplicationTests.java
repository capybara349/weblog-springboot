package com.capybara349.weblogweb;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class WeblogWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testLog() {
        log.info("Hello World");
        log.warn("Hello World");
        log.error("Hello World");
        log.info("Hello World{}", "!");
    }

}

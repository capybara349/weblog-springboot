package com.capybara349.weblog.web;

import com.capybara349.weblog.common.domain.dos.UserDO;
import com.capybara349.weblog.common.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootTest
@Slf4j
class WeblogWebApplicationTests {

    @Autowired
    private UserMapper userMapper;

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


    @Test
    void insertTest() {
        UserDO userDO = UserDO.builder()
                .username("capybara349")
                .password("123456")
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(false)
                .build();
        userMapper.insert(userDO);
    }

    @Test
    void selectTest() {
        userMapper.selectById(1);
    }

    @Test
    void insertTwoUsersTest() {

        UserDO admin = UserDO.builder()
                .username("admin")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(false)
                .build();
        userMapper.insert(admin);

        UserDO test = UserDO.builder()
                .username("test")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(false)
                .build();
        userMapper.insert(test);
    }
}

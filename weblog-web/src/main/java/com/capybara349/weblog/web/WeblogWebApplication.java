package com.capybara349.weblog.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.capybara349.*"})
public class WeblogWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeblogWebApplication.class, args);
    }

}

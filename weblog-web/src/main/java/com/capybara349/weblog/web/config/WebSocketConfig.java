package com.capybara349.weblog.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 11:21
 */
@Configuration
public class WebSocketConfig {

    /**
     * 注册 WebSocket 端点（使用 @ServerEndpoint 注解声明的方式）
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}

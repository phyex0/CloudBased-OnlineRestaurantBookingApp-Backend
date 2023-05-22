package com.upspoon.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig {
    @Bean
    WebSecurityCustomizer webSecurityCustomizerUser() {
        return (web) -> web.ignoring()
                .requestMatchers("/api/user/create/**")
                .requestMatchers(HttpMethod.OPTIONS, "/**");
    }
}

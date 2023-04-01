package com.upspoon.common.config;

import com.upspoon.common.exceptions.CustomFeignErrorDecoder;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.cloud.openfeign.encoding.FeignClientEncodingProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author burak.yesildal
 */

@Configuration
public class CustomFeignConfiguration extends FeignClientEncodingProperties {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomFeignErrorDecoder();
    }


    @Bean
    public FeignContext feignContext() {
        return new FeignContext();
    }

    @Bean
    public RequestInterceptor feignRequestInterceptor() {
        return template -> {
            if (RequestContextHolder.getRequestAttributes() != null) {
                String authorization = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest().getHeader("Authorization");
                if (authorization != null) {
                    template.header("Authorization", authorization);
                }
            }
        };
    }
}
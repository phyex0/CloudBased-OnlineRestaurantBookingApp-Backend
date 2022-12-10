package com.upsoon.common.config;

import com.upsoon.common.exceptions.CustomFeignErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.encoding.FeignClientEncodingProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author burak.yesildal
 */

@Configuration
public class CustomFeignConfiguration extends FeignClientEncodingProperties {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomFeignErrorDecoder();
    }
}

package com.upsoon.common.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author burak.yesildal
 */

public class CustomFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Map message = objectMapper.readValue(response.body().asInputStream(), Map.class);
            return new Exception(message.toString());
        } catch (IOException e) {
            throw new RuntimeException(new HashMap<>().toString());
        }
    }
}

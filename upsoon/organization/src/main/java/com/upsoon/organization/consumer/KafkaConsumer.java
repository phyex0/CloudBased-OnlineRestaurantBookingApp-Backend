package com.upsoon.organization.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsoon.common.kafkaTemplateDTO.OrganizationToOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Halit Burak Yeşildal
 */

@Service
@Slf4j
public class KafkaConsumer {


    private final ObjectMapper objectMapper;

    public KafkaConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-id}")
    public void listen(String message) throws JsonProcessingException {
        var object = objectMapper.readValue(message, OrganizationToOrder.class);
        //following logic
        log.info("Consume :" + message);
    }

}

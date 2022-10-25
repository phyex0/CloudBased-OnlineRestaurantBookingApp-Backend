package com.upsoon.order.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsoon.common.kafkaTemplateDTO.OrganizationToOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

@Service
@Slf4j
public class KafkaProducer {

    @Value("${kafka.topic-organization-create-fail}")
    private String organizationCreateFail;

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;


    public KafkaProducer(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }


    public void produceOrganizationCreateFail(OrganizationToOrder organizationToOrder) {

        String transferredMessage = null;
        try {
            transferredMessage = objectMapper.writeValueAsString(organizationToOrder);
            kafkaTemplate.send(organizationCreateFail, UUID.randomUUID().toString(), transferredMessage);
        } catch (Exception e) {
            log.info("Organization Create Event Failed is failed :D");
        }
        log.info("Message:" + transferredMessage);

    }

}

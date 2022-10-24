package com.upsoon.organization.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsoon.common.kafkaTemplateDTO.OrganizationToOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Halit Burak Yeşildal
 */

@Service
@Slf4j
public class KafkaProducer {

    @Value("${kafka.topic-organization-create}")
    private String topicName;

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;


    public KafkaProducer(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }


    public void produce(OrganizationToOrder organizationToOrder) throws JsonProcessingException {
        String transferredMessage = objectMapper.writeValueAsString(organizationToOrder);
        kafkaTemplate.send(topicName, transferredMessage);
        log.info("Produce :" + transferredMessage);
    }

}

package com.upsoon.organization.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upsoon.organization.service.OrganizationService;
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
    private final OrganizationService organizationService;

    public KafkaConsumer(ObjectMapper objectMapper, OrganizationService organizationService) {
        this.objectMapper = objectMapper;
        this.organizationService = organizationService;
    }


    @KafkaListener(topics = "${kafka.topic-organization-create-fail}", groupId = "${kafka.group-id}")
    public void listen(String message) throws JsonProcessingException {
        var object = objectMapper.readValue(message, OrganizationToOrder.class);
        organizationService.deleteRestaurant(object.getOrganizationId());
        log.info("Consume :" + message);
    }

}

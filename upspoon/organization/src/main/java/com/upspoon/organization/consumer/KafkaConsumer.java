package com.upspoon.organization.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upspoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upspoon.organization.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author burak.yesildal
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
    public void listen(@Payload String message) {
        OrganizationToOrder object = null;
        try {
            object = objectMapper.readValue(message, OrganizationToOrder.class);
            organizationService.deleteRestaurant(object.getOrganizationId());
        } catch (Exception e) {
            log.info("Organization create fail - mapper exception");
        }
        log.info("Consume :" + message);
    }

}

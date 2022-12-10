package com.upspoon.organization.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upspoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upspoon.organization.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Service
@Slf4j
public class KafkaProducer {

    @Value("${kafka.topic-organization-create}")
    private String topicName;

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final OrganizationService organizationService;


    public KafkaProducer(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate, OrganizationService organizationService) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.organizationService = organizationService;
    }


    public void produce(OrganizationToOrder organizationToOrder) {
        String transferredMessage = null;
        try {
            transferredMessage = objectMapper.writeValueAsString(organizationToOrder);
            kafkaTemplate.send(topicName, UUID.randomUUID().toString(), transferredMessage);
        } catch (Exception e) {
            log.info("Organization create event failed. Mapper failed");
            organizationService.deleteRestaurant(organizationToOrder.getOrganizationId());

        }
        log.info("Produce :" + transferredMessage);
    }

}

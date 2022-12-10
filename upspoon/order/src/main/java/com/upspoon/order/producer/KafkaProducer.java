package com.upspoon.order.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upspoon.common.kafkaTemplateDTO.OrderToStock;
import com.upspoon.common.kafkaTemplateDTO.OrganizationToOrder;
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

    @Value("${kafka.topic-organization-create-fail}")
    private String organizationCreateFail;

    @Value("${kafka.topic-order-create}")
    private String orderCreate;


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

    public void produceOrderCreatedEvent(OrderToStock orderToStock) {
        String transferredMessage = null;

        try {
            transferredMessage = objectMapper.writeValueAsString(orderToStock);
            kafkaTemplate.send(orderCreate, UUID.randomUUID().toString(), transferredMessage);
        } catch (Exception e) {
            log.info("Order Create Event failed");
        }
        log.info("Message:" + transferredMessage);
    }

}

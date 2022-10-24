package com.upsoon.order.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upsoon.order.service.OrderService;
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

    private final OrderService orderService;


    public KafkaConsumer(ObjectMapper objectMapper, OrderService orderService) {
        this.objectMapper = objectMapper;
        this.orderService = orderService;
    }


    @KafkaListener(topics = "${kafka.topic-organization-create}", groupId = "${kafka.group-id}")
    public void listen(String message) throws JsonProcessingException {
        var object = objectMapper.readValue(message, OrganizationToOrder.class);
        orderService.saveOrganization(object);
        log.info("Listen :" + message);
    }
}

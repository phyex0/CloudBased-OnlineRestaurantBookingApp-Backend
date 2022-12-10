package com.upspoon.order.consumer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.upspoon.common.kafkaTemplateDTO.OrderToStock;
import com.upspoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upspoon.order.producer.KafkaProducer;
import com.upspoon.order.service.OrderService;
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

    private final OrderService orderService;

    private final KafkaProducer kafkaProducer;


    public KafkaConsumer(ObjectMapper objectMapper, OrderService orderService, KafkaProducer kafkaProducer) {
        this.objectMapper = objectMapper;
        this.orderService = orderService;
        this.kafkaProducer = kafkaProducer;
    }


    @KafkaListener(topics = "${kafka.topic-organization-create}", groupId = "${kafka.group-id}")
    public void listen(@Payload String message) {
        OrganizationToOrder object = null;
        try {
            object = objectMapper.readValue(message, OrganizationToOrder.class);
            orderService.saveOrganization(object);
        } catch (Exception e) {
            log.info(e.getMessage());
            kafkaProducer.produceOrganizationCreateFail(object);
        }
        log.info("Listen :" + message);
    }

    @KafkaListener(topics = "${kafka.topic-stock-failed}", groupId = "${kafka.group-id}")
    public void listenStockFailedEvent(@Payload String message) {
        OrderToStock object = null;
        try {
            object = objectMapper.readValue(message, OrderToStock.class);
            orderService.rollbackOrder(object);
        } catch (Exception e) {
            log.info(e.getMessage());
//            kafkaProducer.produceOrganizationCreateFail(object);
        }
        log.info("Listen :" + message);
    }
}

package com.upsoon.stock.consumer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsoon.common.kafkaTemplateDTO.OrderToStock;
import com.upsoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upsoon.common.kafkaTemplateDTO.StockToPayment;
import com.upsoon.stock.producer.KafkaProducer;
import com.upsoon.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author Halit Burak Yeşildal
 */

@Service
@Slf4j
public class KafkaConsumer {


    private final ObjectMapper objectMapper;

    private final StockService stockService;

    private final KafkaProducer kafkaProducer;

    public KafkaConsumer(ObjectMapper objectMapper, StockService stockService, KafkaProducer kafkaProducer) {
        this.objectMapper = objectMapper;
        this.stockService = stockService;
        this.kafkaProducer = kafkaProducer;
    }


    @KafkaListener(topics = "${kafka.topic-order-create}", groupId = "${kafka.group-id}")
    public void listenOrderCreatedEvent(@Payload String message) {
        OrderToStock object = null;
        try {
            object = objectMapper.readValue(message, OrderToStock.class);
            stockService.save(object);
        } catch (Exception e) {
            log.info(e.getMessage());
//            kafkaProducer.produceOrganizationCreateFail(object);
        }
        log.info("Listen :" + message);
    }

    @KafkaListener(topics = "${kafka.topic-payment-failed}", groupId = "${kafka.group-id}")
    public void listenPaymentFailed(@Payload String message) {
        StockToPayment object = null;
        try {
            object = objectMapper.readValue(message, StockToPayment.class);
            stockService.rollback(object);
        } catch (Exception e) {
            log.info(e.getMessage());
//            kafkaProducer.produceOrganizationCreateFail(object);
        }
        log.info("Listen :" + message);
    }
}

package com.upspoon.stock.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upspoon.common.kafkaTemplateDTO.OrderToStock;
import com.upspoon.common.kafkaTemplateDTO.StockToPayment;
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


    @Value("${kafka.topic-payment-handle}")
    private String paymentHandle;

    @Value("${kafka.topic-stock-failed}")
    private String stockFailedEvent;


    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;


    public KafkaProducer(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }


    public void producePaymentEvent(StockToPayment stockToPayment) {

        String transferredMessage = null;
        try {
            transferredMessage = objectMapper.writeValueAsString(stockToPayment);
            kafkaTemplate.send(paymentHandle, UUID.randomUUID().toString(), transferredMessage);
        } catch (Exception e) {
            log.info("StockToPayment Event Failed");
        }
        log.info("Message:" + transferredMessage);

    }

    public void produceStockFailedEvent(OrderToStock orderToStock) {

        String transferredMessage = null;
        try {
            transferredMessage = objectMapper.writeValueAsString(orderToStock);
            kafkaTemplate.send(stockFailedEvent, UUID.randomUUID().toString(), transferredMessage);
        } catch (Exception e) {
            log.info("OrderToStock Event Failed is failed :D");
        }
        log.info("Message:" + transferredMessage);

    }


}

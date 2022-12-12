package com.upspoon.payment.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
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


    @Value("${kafka.topic-order-created-successfully}")
    private String orderCreatedSuccessfully;

    @Value("${kafka.topic-payment-failed}")
    private String paymentFailed;


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
            kafkaTemplate.send(orderCreatedSuccessfully, UUID.randomUUID().toString(), transferredMessage);
        } catch (Exception e) {
            log.info("StockToPayment Event Failed");
        }
        log.info("Message:" + transferredMessage);

    }

    public void paymentFailedEvent(StockToPayment stockToPayment) {

        String transferredMessage = null;
        try {
            transferredMessage = objectMapper.writeValueAsString(stockToPayment);
            kafkaTemplate.send(paymentFailed, UUID.randomUUID().toString(), transferredMessage);
            log.info("Payment failed, rollback is triggered");
        } catch (Exception e) {
            log.info("Payment failed roll back is failed");
        }
        log.info("Message:" + transferredMessage);

    }


}

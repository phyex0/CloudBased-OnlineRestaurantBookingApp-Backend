package com.upspoon.payment.consumer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.upspoon.common.kafkaTemplateDTO.StockToPayment;
import com.upspoon.payment.producer.KafkaProducer;
import com.upspoon.payment.service.PaymentService;
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

    private final PaymentService paymentService;

    private final KafkaProducer kafkaProducer;

    public KafkaConsumer(ObjectMapper objectMapper, PaymentService paymentService, KafkaProducer kafkaProducer) {
        this.objectMapper = objectMapper;
        this.paymentService = paymentService;
        this.kafkaProducer = kafkaProducer;
    }


    @KafkaListener(topics = "${kafka.topic-payment-handle}", groupId = "${kafka.group-id}")
    public void listenDoPayment(@Payload String message) {
        StockToPayment object = null;
        try {
            object = objectMapper.readValue(message, StockToPayment.class);
            paymentService.doPayment(object);
        } catch (Exception e) {
            log.info(e.getMessage());
            //TODO: stock roll back
//            kafkaProducer.produceOrganizationCreateFail(object);
        }
        log.info("Listen :" + message);
    }
}

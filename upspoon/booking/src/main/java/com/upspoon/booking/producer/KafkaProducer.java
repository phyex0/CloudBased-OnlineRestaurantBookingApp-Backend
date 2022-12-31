package com.upspoon.booking.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upspoon.common.kafkaTemplateDTO.OrganizationToBooking;
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

    @Value("${kafka.topic-booking-create-fail}")
    private String topicName;

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;


    public KafkaProducer(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }


    public void produce(OrganizationToBooking organizationToBooking) {
        String transferredMessage = null;
        try {
            transferredMessage = objectMapper.writeValueAsString(organizationToBooking);
            kafkaTemplate.send(topicName, UUID.randomUUID().toString(), transferredMessage);
        } catch (Exception e) {
            log.info("Booking create event failed.");
        }
        log.info("Produce :" + transferredMessage);
    }

}

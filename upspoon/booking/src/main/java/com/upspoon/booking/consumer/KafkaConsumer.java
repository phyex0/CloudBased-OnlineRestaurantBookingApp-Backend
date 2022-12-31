package com.upspoon.booking.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upspoon.booking.service.BookService;
import com.upspoon.common.kafkaTemplateDTO.OrganizationToBooking;
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
    private final BookService bookService;

    public KafkaConsumer(ObjectMapper objectMapper, BookService bookService) {
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }


    @KafkaListener(topics = "${kafka.topic-booking-create}", groupId = "${kafka.group-id}")
    public void listen(@Payload String message) {
        OrganizationToBooking object = null;
        try {
            object = objectMapper.readValue(message, OrganizationToBooking.class);
            bookService.createOrganization(object);
        } catch (Exception e) {
            log.info("Organization create fail - mapper exception");
        }
        log.info("Consume :" + message);
    }

}

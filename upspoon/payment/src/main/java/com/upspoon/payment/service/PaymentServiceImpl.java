package com.upspoon.payment.service;

import com.upspoon.common.enums.PaymentStatus;
import com.upspoon.common.kafkaTemplateDTO.StockToPayment;
import com.upspoon.payment.mapper.PaymentHistoryMapper;
import com.upspoon.payment.producer.KafkaProducer;
import com.upspoon.payment.repository.PaymentHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @author burak.yesildal
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentHistoryMapper paymentHistoryMapper;

    private final PaymentHistoryRepository paymentHistoryRepository;

    private final KafkaProducer kafkaProducer;

    public PaymentServiceImpl(PaymentHistoryMapper paymentHistoryMapper, PaymentHistoryRepository paymentHistoryRepository, KafkaProducer kafkaProducer) {
        this.paymentHistoryMapper = paymentHistoryMapper;
        this.paymentHistoryRepository = paymentHistoryRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    @Transactional
    public void doPayment(StockToPayment stockToPayment) {
        var paymentHistory = paymentHistoryMapper.toEntity(stockToPayment);
        var previousOperation = paymentHistoryRepository.existsByUserId(paymentHistory.getUserId());
        if (!previousOperation) {
            paymentHistory.setPaymentStatus(PaymentStatus.PAYMENT_DONE);
            paymentHistoryRepository.save(paymentHistory);
            kafkaProducer.producePaymentEvent(stockToPayment);
        } else {
            Random random = new Random();
            int chance = random.nextInt(2);

            if (chance == 1) {
                paymentHistory.setPaymentStatus(PaymentStatus.PAYMENT_DONE);
                kafkaProducer.producePaymentEvent(stockToPayment);
            } else {
                paymentHistory.setPaymentStatus(PaymentStatus.PAYMENT_FAILED);
                kafkaProducer.paymentFailedEvent(stockToPayment);
            }
            paymentHistoryRepository.save(paymentHistory);


        }


    }
}

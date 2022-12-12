package com.upspoon.payment.service;

import com.upspoon.common.kafkaTemplateDTO.StockToPayment;

/**
 * @author burak.yesildal
 */
public interface PaymentService {

    void doPayment(StockToPayment stockToPayment);

}

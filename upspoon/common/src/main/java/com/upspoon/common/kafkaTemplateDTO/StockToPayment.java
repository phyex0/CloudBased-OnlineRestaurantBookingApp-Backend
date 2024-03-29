package com.upspoon.common.kafkaTemplateDTO;

import com.upspoon.common.dto.Order.CreditCardDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockToPayment implements Serializable {
    private static final long serialVersionUID = -6151181695342279874L;

    private UUID userId;

    private Double price;

    private UUID orderId;

    private CreditCardDTO creditCardDTO;

}

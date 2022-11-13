package com.upsoon.common.kafkaTemplateDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockToPayment implements Serializable {
    private static final long serialVersionUID = -6151181695342279874L;

    private UUID userId;

    private Double price;

    private UUID orderId;

}

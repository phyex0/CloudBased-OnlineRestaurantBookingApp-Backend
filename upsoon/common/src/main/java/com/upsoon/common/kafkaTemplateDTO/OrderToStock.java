package com.upsoon.common.kafkaTemplateDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderToStock implements Serializable {
    private static final long serialVersionUID = -8400233693699183103L;

    private UUID orderId;

    private Map<UUID, Integer> productMap;

    private Double price;

}

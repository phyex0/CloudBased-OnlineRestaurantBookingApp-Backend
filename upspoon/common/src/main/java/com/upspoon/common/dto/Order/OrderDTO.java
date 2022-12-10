package com.upspoon.common.dto.Order;

import com.upspoon.common.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = -3184338106480767194L;

    private UUID userId;

    private List<UUID> productId;

    private OrderStatus orderStatus;

    private String orderNote;

}

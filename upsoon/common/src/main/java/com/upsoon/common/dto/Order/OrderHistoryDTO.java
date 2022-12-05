package com.upsoon.common.dto.Order;

import com.upsoon.common.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.nio.DoubleBuffer;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @author burak.yesildal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryDTO implements Serializable {
    private static final long serialVersionUID = -2092363473616534412L;

    private OrderStatus orderStatus;

    private String orderNote;

    private transient List<UUID> productId;

    private List<ProductDTO> productDTOList;

    private Date orderDate;

    private Double totalAmount;
}

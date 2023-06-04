package com.upspoon.common.dto.Order;

import jakarta.validation.constraints.NotNull;
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
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = -1005738152875737310L;

    private UUID id;

    private String productCode;

    @NotNull
    private String productName;

    @NotNull
    private String description;

    @NotNull
    private Double price;

    private String productImage;

    private Long stock;
}

package com.upspoon.common.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author burak.yesildal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = -1005738152875737310L;

    @NotNull
    private String productCode;

    @NotNull
    private String productName;

    @NotNull
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private String productImage;
}

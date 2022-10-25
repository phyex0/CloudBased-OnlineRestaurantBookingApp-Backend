package com.upsoon.common.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Halit Burak Yeşildal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {

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

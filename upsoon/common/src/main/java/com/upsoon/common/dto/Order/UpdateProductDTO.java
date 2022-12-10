package com.upsoon.common.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDTO implements Serializable {
    private static final long serialVersionUID = -4040254592824722230L;

    private String productCode;


    private String productName;


    private String description;


    private Double price;


    private String productImage;
}

package com.upsoon.common.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDTO implements Serializable {


    private String productCode;


    private String productName;


    private String description;


    private Double price;


    private String productImage;
}

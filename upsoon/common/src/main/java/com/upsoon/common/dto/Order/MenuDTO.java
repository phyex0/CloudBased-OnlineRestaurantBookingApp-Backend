package com.upsoon.common.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author Halit Burak Yeşildal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO implements Serializable {

    @NotNull
    private String name;

    private List<ProductDTO> productList;

}

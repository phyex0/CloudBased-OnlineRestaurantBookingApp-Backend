package com.upspoon.common.dto.Order;

import jakarta.validation.constraints.NotNull;
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
public class MenuDTO implements Serializable {
    private static final long serialVersionUID = 2975167333662913722L;
    private UUID id;

    @NotNull
    private String name;

    private List<ProductDTO> productList;


    public MenuDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}

package com.upspoon.common.dto.Stock;

import jakarta.validation.constraints.Min;
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
public class CreateStockDTO implements Serializable {
    private static final long serialVersionUID = 5803990988986220078L;

    @NotNull
    private UUID productId;
    @Min(1L)
    private Long count;

}

package com.upsoon.common.dto.Stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
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

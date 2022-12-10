package com.upspoon.common.dto.Order;


import com.upspoon.common.enums.BusinessTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @author burak.yesildal
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDTO implements Serializable {
    private static final long serialVersionUID = 5618035418244149764L;

    private UUID id;

    @NotNull
    private BusinessTypes businessTypes;

    private String businessImage;

    private List<MenuDTO> menuList;

}

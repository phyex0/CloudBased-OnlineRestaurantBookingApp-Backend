package com.upsoon.common.dto.Order;


import com.upsoon.common.enums.BusinessTypes;
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
public class BusinessDTO implements Serializable {

    @NotNull
    private BusinessTypes businessTypes;

    private String businessImage;

    private List<MenuDTO> menuList;

}

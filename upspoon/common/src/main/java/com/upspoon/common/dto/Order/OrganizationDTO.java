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
public class OrganizationDTO implements Serializable {
    private static final long serialVersionUID = -4026175470931358111L;

    private UUID id;

    @NotNull
    private String organizationName;

    private BusinessTypes businessTypes;

    private String organizationImage;

    private List<MenuDTO> menuList;


}

package com.upspoon.common.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDTOForUI implements Serializable {
    private static final long serialVersionUID = 4009917837742108744L;

    private UUID organizationId;

    private UUID businessId;

    private String organizationName;

    private String businessImage;
}

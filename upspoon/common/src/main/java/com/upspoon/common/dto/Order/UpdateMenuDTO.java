package com.upspoon.common.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMenuDTO implements Serializable {
    private static final long serialVersionUID = 1529099946467250818L;

    private String name;

}

package com.upspoon.common.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBusinessDTO implements Serializable {
    private static final long serialVersionUID = -2817804597407448815L;

    private String businessImage;
}

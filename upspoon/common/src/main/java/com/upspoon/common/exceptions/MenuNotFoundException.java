package com.upspoon.common.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class MenuNotFoundException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 5113182699846602226L;

    public MenuNotFoundException() {
        super("Menu not found");
    }
}

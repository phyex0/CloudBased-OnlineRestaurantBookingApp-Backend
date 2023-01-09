package com.upspoon.common.exceptions;

import java.io.Serializable;

/**
 * @author burak.yesildal
 */

public class MissingProductsException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -670717010857848141L;

    public MissingProductsException(String message) {
        super(message);
    }
}

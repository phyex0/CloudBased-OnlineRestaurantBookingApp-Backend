package com.upspoon.common.exceptions;

import java.io.Serializable;

/**
 * @author burak.yesildal
 */


public class BusinessNotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -3013694239648602474L;

    public BusinessNotFoundException(String message) {
        super(message);
    }
}

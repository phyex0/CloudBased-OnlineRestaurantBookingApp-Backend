package com.upspoon.common.exceptions;

import java.io.Serializable;

/**
 * @author burak.yesildal
 */


public class BusinessTypeDoesNotRecognisedException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -1287964818687421443L;

    public BusinessTypeDoesNotRecognisedException(String message) {
        super(message);
    }
}

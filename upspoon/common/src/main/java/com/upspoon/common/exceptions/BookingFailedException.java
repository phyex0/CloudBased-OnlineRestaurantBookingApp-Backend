package com.upspoon.common.exceptions;

import java.io.Serializable;

/**
 * @author burak.yesildal
 */


public class BookingFailedException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1640327226759968893L;

    public BookingFailedException(String message) {
        super(message);
    }
}

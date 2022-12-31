package com.upspoon.common.exceptions;

import java.io.Serializable;

/**
 * @author burak.yesildal
 */


public class AlreadyBookedTableException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 5355354265284941370L;

    public AlreadyBookedTableException(String message) {
        super(message);
    }
}

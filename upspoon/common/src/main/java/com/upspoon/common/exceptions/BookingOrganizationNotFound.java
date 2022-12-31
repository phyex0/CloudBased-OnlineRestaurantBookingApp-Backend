package com.upspoon.common.exceptions;

import java.io.Serializable;

/**
 * @author burak.yesildal
 */


public class BookingOrganizationNotFound extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1368704729533518605L;

    public BookingOrganizationNotFound(String message) {
        super(message);
    }
}

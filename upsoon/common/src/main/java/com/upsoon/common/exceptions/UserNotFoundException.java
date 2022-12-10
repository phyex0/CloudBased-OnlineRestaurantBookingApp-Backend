package com.upsoon.common.exceptions;

import java.io.Serializable;

/**
 * @author burak.yesildal
 */

public class UserNotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -2118865256961489954L;

    public UserNotFoundException() {
        super("User cannot be found by given Id");
    }
}

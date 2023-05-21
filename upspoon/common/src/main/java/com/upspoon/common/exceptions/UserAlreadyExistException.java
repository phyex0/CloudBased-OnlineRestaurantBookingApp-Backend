package com.upspoon.common.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class UserAlreadyExistException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = -4304795009445994583L;
}

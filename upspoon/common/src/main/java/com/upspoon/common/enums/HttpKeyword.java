package com.upspoon.common.enums;

import java.util.Arrays;

/**
 * @author burak.yesildal
 */
public enum HttpKeyword {


    AUTHORIZATION("Authorization"),
    BEARER("Bearer ");

    public final String codeName;

    HttpKeyword(String codeName) {
        this.codeName = codeName;
    }

    public static HttpKeyword getCodeName(String codeName) {
        return Arrays.stream(HttpKeyword.values()).filter(item -> item.codeName.equals(codeName)).findFirst().get();

    }
}

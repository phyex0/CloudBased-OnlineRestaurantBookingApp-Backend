package com.upspoon.common.enums;

import java.util.Arrays;

/**
 * @author burak.yesildal
 */

public enum BusinessTypes {

    MARKET("market"),

    RESTAURANT("restaurant"),

    BOOK("book");

    private final String codeName;

    BusinessTypes(String codeName) {
        this.codeName = codeName;
    }

    public static BusinessTypes getCodeName(String codeName) {
        return Arrays.stream(BusinessTypes.values()).filter(item -> item.codeName.equals(codeName)).findFirst().get();
    }
}

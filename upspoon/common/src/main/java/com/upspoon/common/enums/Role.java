package com.upspoon.common.enums;

import java.util.Arrays;

/**
 * @author burak.yesildal
 */
public enum Role {

    ADMIN_ROLE("ADMIN_ROLE"),
    USER_ROLE("USER_ROLE"),
    ORGANIZATION_ROLE("ORGANIZATION_ROLE"),
    BUSINESS_ROLE("BUSINESS_ROLE");

    public final String codeName;

    Role(String codeName) {
        this.codeName = codeName;
    }

    public static Role getCodeName(String codeName) {
        return Arrays.stream(Role.values()).filter(item -> item.codeName.equals(codeName)).findFirst().get();

    }
}

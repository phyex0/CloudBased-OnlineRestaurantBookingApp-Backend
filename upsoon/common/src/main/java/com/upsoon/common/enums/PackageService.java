package com.upsoon.common.enums;


import java.util.Arrays;

/**
 * @author burak.yesildal
 */

public enum PackageService {

    NO_CARRIER("no_carrier"),
    OWN_CARRIER("own_carrier"),
    OUT_SOURCE_CARRIER("out_source_carrier");

    public final String codeName;

    PackageService(String codeName) {
        this.codeName = codeName;
    }

    public static PackageService getCodeName(String codeName) {
        return Arrays.stream(PackageService.values()).filter(item -> item.codeName.equals(codeName)).findFirst().get();

    }
}

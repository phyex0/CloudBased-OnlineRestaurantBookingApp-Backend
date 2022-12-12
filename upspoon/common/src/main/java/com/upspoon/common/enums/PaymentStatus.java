package com.upspoon.common.enums;

import java.util.Arrays;

/**
 * @author burak.yesildal
 */

public enum PaymentStatus {

    PAYMENT_DONE("payment_done"),
    PAYMENT_FAILED("payment_failed");


    public final String codeName;

    PaymentStatus(String codeName) {
        this.codeName = codeName;
    }

    public static PaymentStatus getCodeName(String codeName) {
        return Arrays.stream(PaymentStatus.values()).filter(item -> item.codeName.equals(codeName)).findFirst().get();

    }
}

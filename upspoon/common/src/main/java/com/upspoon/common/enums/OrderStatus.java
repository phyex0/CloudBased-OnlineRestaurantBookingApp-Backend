package com.upspoon.common.enums;

import java.util.Arrays;

/**
 * @author burak.yesildal
 */

public enum OrderStatus {

    ORDER_CREATED("order_create"),
    PAYMENT_FAILED("payment_failed"),
    SUCCESS("success"),
    ORDER_PREPARING("order_preparing"),
    INVALID_STOCK("invalid_stock"),
    INVALID_USER("invalid_user"),
    INVALID_PRODUCT("invalid_product"),
    OUT_OF_ORDER("out_of_order");

    public final String codeName;

    OrderStatus(String codeName) {
        this.codeName = codeName;
    }

    public static OrderStatus getCodeName(String codeName) {
        return Arrays.stream(OrderStatus.values()).filter(item -> item.codeName.equals(codeName)).findFirst().get();

    }
}

package com.upspoon.common.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author burak.yesildal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDTO implements Serializable {
    private static final long serialVersionUID = -8568583806294677600L;
    private String cardNumber;

    private String day;

    private String month;

    private String cvc;

    private String cardOwnerFullName;
}

package com.upspoon.common.dto.Booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author burak.yesildal
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailDTO implements Serializable {
    private static final long serialVersionUID = 4499367857778885249L;
    private UUID id;
    private String fullName;
    private String phoneNumber;
    private Integer numberOfPeople;
    private Date bookDate;
    private Integer tableNumber;
    private UUID userId;
}

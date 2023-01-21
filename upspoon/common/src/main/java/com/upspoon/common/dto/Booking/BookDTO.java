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
public class BookDTO implements Serializable {
    private static final long serialVersionUID = -2089640579610893864L;
    private UUID id;
    private Date date;
    private Integer reservationCount;
}

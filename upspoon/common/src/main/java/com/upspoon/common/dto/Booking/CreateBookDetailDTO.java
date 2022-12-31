package com.upspoon.common.dto.Booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author burak.yesildal
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookDetailDTO implements Serializable {
    private static final long serialVersionUID = 7288366831553603305L;

    private UUID id;

    private String fullName;

    private String phoneNumber;

    private Integer numberOfPeople;

    private Date bookDate;

    @Min(1)
    private Integer tableNumber;
}

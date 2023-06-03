package com.upspoon.common.dto.Booking;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
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

    private LocalDate bookDate;

    @Min(1)
    private Integer tableNumber;

    private UUID userId;
}

package com.upspoon.common.dto.Organization;

import lombok.*;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RestaurantUserDTO implements Serializable {
    private static final long serialVersionUID = -6503234721562743348L;

    private UUID id;

    private String name;

    private String lastName;

    private String middleName;

    private String phoneNumber;

    private String email;

    private Set<OrganizationDTO> organizations;
}

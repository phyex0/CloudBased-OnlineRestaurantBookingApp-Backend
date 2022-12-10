package com.upsoon.common.dto.Organization;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

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

    private String name;

    private String lastName;

    private String middleName;

    private String phoneNumber;

    private String email;

    private Set<OrganizationDTO> organizations;
}

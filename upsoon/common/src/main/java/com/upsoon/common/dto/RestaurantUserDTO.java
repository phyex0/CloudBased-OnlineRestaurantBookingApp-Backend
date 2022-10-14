package com.upsoon.common.dto;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RestaurantUserDTO implements Serializable {

    private String name;

    private String lastName;

    private String middleName;

    private String phoneNumber;

    private String email;

    private Set<OrganizationDTO> organizations;
}

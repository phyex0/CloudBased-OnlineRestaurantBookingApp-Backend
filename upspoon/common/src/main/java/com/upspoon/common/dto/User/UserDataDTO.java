package com.upspoon.common.dto.User;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5821517733699069292L;

    private UUID id;

    private String firstName;

    private String lastName;

    private String middleName;

    @NotNull
    private String phoneNumber;

    private String address;

    @NotNull
    private String mailAddress;

    private String password;

    private Set<UUID> orderHistory = new HashSet<>();
}

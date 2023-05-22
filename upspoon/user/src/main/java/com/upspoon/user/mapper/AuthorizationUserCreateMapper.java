package com.upspoon.user.mapper;

import com.upspoon.common.dto.AuthorizationUser.UserCreateDTO;
import com.upspoon.common.dto.User.UserDataDTO;
import com.upspoon.common.enums.Role;
import com.upspoon.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorizationUserCreateMapper extends EntityMapper<UserDataDTO, UserCreateDTO> {

    @Override
    @Mapping(target = "role", expression = "java(userRole())")
    @Mapping(target = "firstname", source = "firstName")
    @Mapping(target = "lastname", source = "lastName")
    @Mapping(target = "email", source = "mailAddress")
    UserCreateDTO toEntity(UserDataDTO dto);

    @Override
    UserDataDTO toDto(UserCreateDTO entity);

    default Role userRole() {
        return Role.USER_ROLE;
    }
}

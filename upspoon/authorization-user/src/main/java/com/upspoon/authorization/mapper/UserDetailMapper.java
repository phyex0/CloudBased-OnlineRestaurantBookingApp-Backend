package com.upspoon.authorization.mapper;

import com.upspoon.authorization.model.UserDetail;
import com.upspoon.common.dto.AuthorizationUser.UserCreateDTO;
import com.upspoon.common.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDetailMapper extends EntityMapper<UserCreateDTO, UserDetail> {
    @Override
    UserDetail toEntity(UserCreateDTO dto);

    @Override
    UserCreateDTO toDto(UserDetail entity);
}

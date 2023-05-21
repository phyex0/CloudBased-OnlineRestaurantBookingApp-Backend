package com.upspoon.user.mapper;

import com.upspoon.common.dto.User.UserDataDTO;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.user.model.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserDataMapper extends EntityMapper<UserDataDTO, UserData> {
    @Override
    UserData toEntity(UserDataDTO dto);

    @Override
    UserDataDTO toDto(UserData entity);

    UserData updateEntity(@MappingTarget UserData userData, UserDataDTO userDataDTO);
}

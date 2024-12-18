package com.sokortech.security.mapper;

import com.sokortech.security.config.MapperConfig;
import com.sokortech.security.dto.user.UserRegistrationRequestDto;
import com.sokortech.security.dto.user.UserResponseDto;
import com.sokortech.security.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toUserResponse(User user);
    User toEntity(UserRegistrationRequestDto requestDto);
}

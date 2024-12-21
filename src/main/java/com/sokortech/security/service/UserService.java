package com.sokortech.security.service;

import com.sokortech.security.dto.user.UserRegistrationRequestDto;
import com.sokortech.security.dto.user.UserResponseDto;
import com.sokortech.security.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;

    UserResponseDto getByEmail(String email);
}

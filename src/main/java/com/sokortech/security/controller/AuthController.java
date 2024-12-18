package com.sokortech.security.controller;

import com.sokortech.security.dto.user.UserLoginRequestDto;
import com.sokortech.security.dto.user.UserRegistrationRequestDto;
import com.sokortech.security.dto.user.UserResponseDto;
import com.sokortech.security.exception.RegistrationException;
import com.sokortech.security.security.AuthenticationService;
import com.sokortech.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public UserResponseDto registerUser(@RequestBody UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}

package com.sokortech.security.security;


import com.sokortech.security.dto.user.UserLoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// this class will be used to check if the data credentials sent by user is
// valid or no
@Component
@RequiredArgsConstructor
public class AuthenticationManager {
    private final AuthenticationService authenticationService;

    public boolean isValidAuthentication(UserLoginRequestDto requestDto) {
        return authenticationService.validateCredentials(requestDto).isPresent();
    }
}

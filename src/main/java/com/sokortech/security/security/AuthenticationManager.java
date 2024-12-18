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

    public boolean isValidAuthentication(AuthLoginPasswordObjectToken authLoginPasswordObjectToken) {
        if (authLoginPasswordObjectToken == null) {
            return false;
        }

        // check if email is correct and everything is good with it
        String email = (String)authLoginPasswordObjectToken.getPrincipal();
        String password =
                (String) authLoginPasswordObjectToken.getCredentials();

        return authenticationService.authenticate(
                new UserLoginRequestDto(email, password));
    }
}

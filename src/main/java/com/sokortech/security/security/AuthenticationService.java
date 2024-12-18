package com.sokortech.security.security;

import java.util.Optional;
import com.sokortech.security.dto.user.UserLoginRequestDto;
import com.sokortech.security.model.User;
import com.sokortech.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    public String authenticate(UserLoginRequestDto requestDto) {
        Optional<User> validUser = validateCredentials(requestDto);
        if (validUser.isPresent()) {
            return "Welcome! " + validUser.get().getEmail()
                   + " You have successfully logged in.";
        }
        return "User is not authenticated. Please check your credentials";
    }

    public Optional<User> validateCredentials(UserLoginRequestDto requestDto) {
        Optional<User> userFromRepository =
                userRepository.findByEmail(requestDto.email());
        if (userFromRepository.isPresent() &&
            userFromRepository.get().getPassword().equals(requestDto.password())) {
            return userFromRepository;
        }
        return Optional.empty();
    }
}

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

    public boolean authenticate(UserLoginRequestDto requestDto) {
        Optional<User> userFromRepository =
                userRepository.findByEmail(requestDto.email());
        return userFromRepository.isPresent()
               && userFromRepository.get().getPassword().equals(requestDto.password());
    }
}

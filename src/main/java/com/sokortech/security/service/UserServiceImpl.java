package com.sokortech.security.service;

import com.sokortech.security.dto.user.UserRegistrationRequestDto;
import com.sokortech.security.dto.user.UserResponseDto;
import com.sokortech.security.exception.RegistrationException;
import com.sokortech.security.mapper.UserMapper;
import com.sokortech.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.sokortech.security.model.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with email " + requestDto.getEmail() + " already exists");
        }
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        User savedUser = userRepository.save(user);

        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponseDto getByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> userMapper.toUserResponse(user))
                .orElseThrow(() -> new RuntimeException("Can't find user"));
    }
}

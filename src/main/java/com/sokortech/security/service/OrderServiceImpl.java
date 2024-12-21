package com.sokortech.security.service;

import com.sokortech.security.dto.order.OrderDto;
import com.sokortech.security.dto.user.UserResponseDto;
import com.sokortech.security.mapper.OrderMapper;
import com.sokortech.security.mapper.UserMapper;
import com.sokortech.security.model.Order;
import com.sokortech.security.repository.OrderRepository;
import com.sokortech.security.repository.UserRepository;
import com.sokortech.security.security.AuthLoginPasswordObjectToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findAll(Pageable pageable) {
        List<Order> orders = orderRepository.findAllByIsDeletedFalse(pageable);
        return orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllForSpecificUser(String email, Pageable pageable) {
        UserResponseDto userResponseDto = userService.getByEmail(email);
        return orderRepository.findAllByUserId(userResponseDto.getId())
                .stream()
                .map(orderMapper :: toDto)
                .toList();
    }
}

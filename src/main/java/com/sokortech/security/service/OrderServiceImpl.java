package com.sokortech.security.service;

import com.sokortech.security.dto.order.OrderDto;
import com.sokortech.security.mapper.OrderMapper;
import com.sokortech.security.model.Order;
import com.sokortech.security.repository.OrderRepository;
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

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findAll(Pageable pageable) {
        System.out.println("OrderServiceImpl.findAll IS WORKING");
        List<Order> orders = orderRepository.findAllByIsDeletedFalse(pageable);
        return orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}




//package com.sokortech.security.service;
//
//import java.util.List;
//import com.sokortech.security.model.Order;
//import org.springframework.data.domain.Pageable;
//import com.sokortech.security.repository.OrderRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class OrderServiceImpl implements OrderService{
//    private final OrderRepository orderRepository;
//
//    @Override
//    public List<Order> findAll(Pageable pageable) {
//        System.out.println("OrderServiceImpl.findAll IS WORKING");
//        return orderRepository.findAll();
//    }
//}

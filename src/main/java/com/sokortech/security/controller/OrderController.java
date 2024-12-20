package com.sokortech.security.controller;

import com.sokortech.security.dto.order.OrderDto;
import com.sokortech.security.security.AuthLoginPasswordObjectToken;
import com.sokortech.security.security.SecurityContext;
import com.sokortech.security.security.SecurityContextHolder;
import org.springframework.data.domain.Pageable;
import java.util.List;
import com.sokortech.security.model.Order;
import com.sokortech.security.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> findAll(Pageable pageable) {
        AuthLoginPasswordObjectToken authLoginPasswordObject =
                SecurityContextHolder.getSecurityContext().getAuthLoginPasswordObject();

        String email = (String) authLoginPasswordObject.getPrincipal();
        return orderService.findAllForSpecificUser(email, pageable);
    }


}

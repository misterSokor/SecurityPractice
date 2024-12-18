package com.sokortech.security.service;

        import com.sokortech.security.dto.order.OrderDto;
        import org.springframework.data.domain.Pageable;

        import java.util.List;

public interface OrderService {
   List<OrderDto> findAll(Pageable pageable);
}


package com.sokortech.security.dto.order;

import java.time.LocalDateTime;
import com.sokortech.security.model.Order;
import lombok.Data;

@Data
//public class OrderDto {
//    private Long id;
//    private Long userId;
//    private LocalDateTime orderDate;
//    private Order.Status status;
//
//
//}

// NEW
public class OrderDto {
    private Long id;
    private LocalDateTime orderDate;
    private String status;
    private Long userId;
    private boolean idDeleted;

    // Constructors
    public OrderDto() {
    }

    public OrderDto(Long id, LocalDateTime orderDate, String status, Long userId, boolean idDeleted) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.userId = userId;
        this.idDeleted = idDeleted;
    }
}

package com.sokortech.security.repository;

import com.sokortech.security.dto.order.OrderDto;
import com.sokortech.security.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"user"})
    List<Order> findAllByIsDeletedFalse(Pageable pageable);

    List<Order> findAllByUserId(Long userId);
}



//package com.sokortech.security.repository;
//
//import com.sokortech.security.dto.order.OrderDto;
//import com.sokortech.security.model.Order;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface OrderRepository extends JpaRepository<Order, Long> {
//}

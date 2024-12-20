package com.sokortech.security.repository;

import java.util.List;
import java.util.Optional;
import com.sokortech.security.model.Order;
import com.sokortech.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

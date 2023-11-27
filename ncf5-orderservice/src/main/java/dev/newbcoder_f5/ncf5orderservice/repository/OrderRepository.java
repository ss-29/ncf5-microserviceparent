package dev.newbcoder_f5.ncf5orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.newbcoder_f5.ncf5orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}

package com.example.lotte.repository;

import com.example.lotte.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Các phương thức truy vấn đơn đặt hàng
}

package com.example.lotte.repository;

import com.example.lotte.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Các phương thức truy vấn khách hàng
}

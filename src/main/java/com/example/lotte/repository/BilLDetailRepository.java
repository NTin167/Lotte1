package com.example.lotte.repository;

import com.example.lotte.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilLDetailRepository extends JpaRepository<BillDetail, Long> {
}

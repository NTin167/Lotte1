package com.example.lotte.repository;

import com.example.lotte.model.ReceiptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptDetailRepository extends JpaRepository<ReceiptDetail, Long> {
    List<ReceiptDetail> findAllByReceiptId(Long receiptId);
}

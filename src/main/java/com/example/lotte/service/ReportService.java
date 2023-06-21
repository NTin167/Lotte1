package com.example.lotte.service;

import com.example.lotte.model.Bill;
import com.example.lotte.model.BillDetail;
import com.example.lotte.repository.BilLDetailRepository;
import com.example.lotte.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    BillRepository billRepository;

    @Autowired
    BilLDetailRepository bilLDetailRepository;

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }
    public ResponseEntity<?> getBillDetailsByBillId(Long billId) {
        List<BillDetail> billDetails = bilLDetailRepository.findByBillId(billId);
        if(billDetails.size() == 0) {
            return ResponseEntity.ok("Chi tiết hóa đơn không tồn tại");
        }
        return ResponseEntity.ok(bilLDetailRepository.findByBillId(billId));
    }
}

package com.example.lotte.controller;

import com.example.lotte.model.Bill;
import com.example.lotte.model.BillDetail;
import com.example.lotte.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping
    public List<Bill> getAllBills() {
        return reportService.getAllBills();
    }
    @GetMapping("{id}/getBillDetail")
    public ResponseEntity<?> getBillDetailsByBillId(@PathVariable Long id) {
        return reportService.getBillDetailsByBillId(id);
    }
}

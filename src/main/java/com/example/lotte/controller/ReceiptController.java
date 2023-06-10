package com.example.lotte.controller;

import com.example.lotte.DTO.StockReceivingDTO;
import com.example.lotte.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping(value = "/import", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> importMaterials(@RequestBody StockReceivingDTO stockReceivingDTO) {
        return receiptService.importMaterials(stockReceivingDTO);
    }

    @PostMapping(value = "/complete")
    public ResponseEntity<?> completeReceipt(@RequestParam Long receptId) {
        return receiptService.completeReceipt(receptId);
    }

    @GetMapping()
    public ResponseEntity<?> getAllReceipt() {
        return receiptService.findAllRecipt();
    }

    @PostMapping(value = "/updateReceiptStatus")
    public ResponseEntity<?> updateStatus(@RequestParam Long id, @RequestParam int status) {
        return receiptService.updateReceiptDetailStatus(id, status);
    }

    @GetMapping(value = "/seeDetailReceipt")
    public ResponseEntity<?> seeDetail(Long id) {
        return receiptService.getDetailReceipt(id);
    }
}

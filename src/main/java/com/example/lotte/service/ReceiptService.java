package com.example.lotte.service;

import com.example.lotte.DTO.ItemsDTO;
import com.example.lotte.DTO.ReceiptDTO;
import com.example.lotte.DTO.ReceiptDetailDTO;
import com.example.lotte.DTO.StockReceivingDTO;
import com.example.lotte.exception.ResourceNotFoundException;
import com.example.lotte.model.*;
import com.example.lotte.repository.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private ReceiptDetailRepository receiptDetailRepository;
    @Transactional
    public ResponseEntity<?> importMaterials(StockReceivingDTO stockReceivingDTO){
        System.out.println(stockReceivingDTO.getStaffId());
        System.out.println(stockReceivingDTO.getSupplierId());

        Staff staff = staffRepository.findById(
                stockReceivingDTO.getStaffId()).orElseThrow(()->new ResourceNotFoundException("Staff", "id", stockReceivingDTO.getStaffId()));
        Supplier supplier = supplierRepository.findById(stockReceivingDTO.getSupplierId()).get();
        System.out.println(staff.getName());
        System.out.println(supplier.getName());
        Receipt receipt = new Receipt();
        if(staff == null || supplier == null) {
            return ResponseEntity.ok("Không tìm thấy nhân viên hay nhà cung cấp với ID: " + stockReceivingDTO.getStaffId());
        }
        else {
            receipt.setDate(LocalDateTime.now());
            receipt.setEmployee(staff);
            receipt.setSupplier(supplier);
            receipt.setStatus(0); // chưa hoàn thành
            receiptRepository.save(receipt);
        }

        Integer totalPriceReceipt = 0;
        for (ItemsDTO itemsDTO : stockReceivingDTO.getItemsDTOS()) {
            Long itemId = itemsDTO.getItemId();
            Integer quantity = itemsDTO.getQuantity();

            Material material = materialRepository.findById(itemId).orElse(null);
            if (material == null) {
                return ResponseEntity.ok(new NotFoundException("Không tìm thấy nguyên liệu với ID: " + itemId));
            }
            else {
                //-------------------------

//                material.setStock(material.getStock() + quantity);
//                materialRepository.save(material);


                //-------------------------
                ReceiptDetail receiptDetail = new ReceiptDetail();
                receiptDetail.setReceipt(receipt);
                receiptDetail.setMaterial(material);
                receiptDetail.setQuantity(quantity);
                receiptDetail.setStatus(0);
                receiptDetail.setPrice(material.getPrice());
                if(receipt.getEmployee() != null && receipt.getSupplier() != null) {
                    receiptDetailRepository.save(receiptDetail);
                    totalPriceReceipt = totalPriceReceipt + receiptDetail.getPrice() * receiptDetail.getQuantity();
                }
            }
        }
        receipt.setTotalPrice(totalPriceReceipt);
        receiptRepository.save(receipt);
        return ResponseEntity.ok(receipt);
    }

    public ResponseEntity<?> completeReceipt(Long receiptId) {
        Receipt receipt = receiptRepository.findById(receiptId)
                .orElseThrow(()->new ResourceNotFoundException("receipt", "id", receiptId.toString()));
        receipt.setStatus(1); // đã hoàn thành
        receiptRepository.save(receipt);
        List<ReceiptDetail> receiptDetailList = receiptDetailRepository.findAllByReceiptId(receiptId);
        for (ReceiptDetail itemsReceiptDetail : receiptDetailList) {
            Material material = materialRepository.findById(itemsReceiptDetail.getMaterial().getId()).orElse(null);
            if (material == null) {
                return ResponseEntity.ok(
                        new NotFoundException("Không tìm thấy nguyên liệu với ID: " + itemsReceiptDetail.getMaterial().getId()));
            }
            else {
                System.out.println(itemsReceiptDetail.getReceipt().getId()+ "id truoc, quantity sau" + itemsReceiptDetail.getQuantity());
                material.setStock(material.getStock() + itemsReceiptDetail.getQuantity());
                materialRepository.save(material);
            }
        }
        return ResponseEntity.ok(receiptDetailList);
    }

    public ResponseEntity<?> seeReceiptDetail(Long receiptId) {
        Receipt receipt = receiptRepository.findById(receiptId).get();
        ReceiptDTO receiptDTO = new ReceiptDTO();
        if(receipt == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            receiptDTO.setId(receipt.getId());
            receiptDTO.setDate(receipt.getDate());
            receiptDTO.setEmployee(receipt.getEmployee().getName());
            receiptDTO.setSupplier(receipt.getEmployee().getName());
        }
        return ResponseEntity.ok(receiptDTO);

    }

    public ResponseEntity<?> updateReceiptStatus(Long id, int status) {
        Receipt detail = receiptRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("receipt", "id", id.toString()));
        detail.setStatus(status);
        receiptRepository.save(detail);
        return ResponseEntity.ok(detail);
    }

    public ResponseEntity<?> findAllRecipt() {
        List<Receipt> receipts = receiptRepository.findAll();
        List<ReceiptDTO> receiptDTOS = new ArrayList<>();
        for (Receipt receipt : receipts) {
            ReceiptDTO receiptDTO = new ReceiptDTO();
            // Map properties from Receipt to ReceiptDTO
            receiptDTO.setId(receipt.getId());
            receiptDTO.setDate(receipt.getDate());
            receiptDTO.setEmployee(receipt.getEmployee().getName());
            receiptDTO.setSupplier(receipt.getSupplier().getName());
            receiptDTO.setStatus(receipt.getStatus());
            receiptDTO.setTotalPrice(receipt.getTotalPrice());
            receiptDTOS.add(receiptDTO);
        }
        return ResponseEntity.ok(receiptDTOS);
    }

    public ResponseEntity<?> getDetailReceipt(Long id) {
        List<ReceiptDetail> detail = receiptDetailRepository.findAllByReceiptId(id);
        List<ReceiptDetailDTO> receiptDetailDTOS = new ArrayList<>();
        for(ReceiptDetail receiptDetail : detail) {
            ReceiptDetailDTO receiptDetailDTO = new ReceiptDetailDTO();

            receiptDetailDTO.setId(receiptDetail.getId());
            receiptDetailDTO.setReceipt(receiptDetail.getReceipt().getId());
            receiptDetailDTO.setMaterial(receiptDetail.getMaterial().getName());
            receiptDetailDTO.setQuantity(receiptDetail.getQuantity());
            receiptDetailDTO.setStatus(receiptDetail.getStatus());
            receiptDetailDTO.setPrice(receiptDetail.getPrice());
            receiptDetailDTOS.add(receiptDetailDTO);
        }
        return ResponseEntity.ok(receiptDetailDTOS);
    }
}

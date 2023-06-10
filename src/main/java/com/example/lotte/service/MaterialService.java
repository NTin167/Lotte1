package com.example.lotte.service;

import com.example.lotte.DTO.ItemsDTO;
import com.example.lotte.DTO.MaterialDTO;
import com.example.lotte.DTO.StockReceivingDTO;
import com.example.lotte.model.*;
import com.example.lotte.repository.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ReceiptDetailRepository receiptDetailRepository;


    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }
    public Page<Material> getAllMaterials(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return materialRepository.findAll(pageable);
    }
    public Material createMaterial(Material material) {
        return materialRepository.save(material);
    }

    public Material getMaterialById(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid material ID: " + id));
    }

    public Material updateMaterial(Long id, Material updatedMaterial) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid material ID: " + id));

        material.setName(updatedMaterial.getName());
        material.setPrice(updatedMaterial.getPrice());
        material.setUnit(updatedMaterial.getUnit());
//        material.setStock(updatedMaterial.getStock());
        return materialRepository.save(material);
    }

    public void deleteMaterial(Long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid material ID: " + id));

        materialRepository.delete(material);
    }

    @Transactional
    public ResponseEntity<?> importMaterials(StockReceivingDTO stockReceivingDTO){
        Employee employee = employeeRepository.findById(stockReceivingDTO.getStaffId()).get();
        Supplier supplier = supplierRepository.findById(stockReceivingDTO.getSupplierId()).get();
        Receipt receipt = new Receipt();
        if(employee == null || supplier == null) {
            return ResponseEntity.ok("Không tìm thấy nhân viên hay nhà cung cấp với ID: " + stockReceivingDTO.getStaffId());
        }
        else {
            receipt.setDate(new Date());
            receipt.setEmployee(employee);
            receipt.setSupplier(supplier);
            receiptRepository.save(receipt);
        }



        for (ItemsDTO itemsDTO : stockReceivingDTO.getItemsDTOS()) {
            Long itemId = itemsDTO.getItemId();
            Integer quantity = itemsDTO.getQuantity();

            Material material = materialRepository.findById(itemId).orElse(null);
            if (material == null) {
                return ResponseEntity.ok(new NotFoundException("Không tìm thấy nguyên liệu với ID: " + itemId));
            }
            else {
                material.setStock(material.getStock() + quantity);
                materialRepository.save(material);

                ReceiptDetail receiptDetail = new ReceiptDetail();
                receiptDetail.setReceipt(receipt);
                receiptDetail.setMaterial(material);
                if(receipt.getEmployee() != null && receipt.getSupplier() != null) {
                    receiptDetailRepository.save(receiptDetail);
                }
            }
        }
        return ResponseEntity.ok("Nhập nguyên liệu thành công");
    }
}

package com.example.lotte.service;

import com.example.lotte.model.*;
import com.example.lotte.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private StaffRepository staffRepository;

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



}

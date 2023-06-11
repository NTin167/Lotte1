package com.example.lotte.service;

import com.example.lotte.model.Supplier;
import com.example.lotte.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
}

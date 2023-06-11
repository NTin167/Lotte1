package com.example.lotte.controller;

import com.example.lotte.DTO.ItemsDTO;
import com.example.lotte.DTO.MaterialDTO;
import com.example.lotte.DTO.StockReceivingDTO;
import com.example.lotte.builder.MaterialBuilder;
import com.example.lotte.builder.MaterialConcreteBuilder;
import com.example.lotte.model.Material;
import com.example.lotte.model.Supplier;
import com.example.lotte.response.ErrorResponse;
import com.example.lotte.response.PaginationResponse;
import com.example.lotte.service.MaterialService;
import com.example.lotte.service.SupplierService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private static int STOCK = 0;


    @Autowired
    MaterialService materialService;

    @Autowired
    SupplierService supplierService;

    @GetMapping
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @PostMapping
    public Material createMaterial(@RequestBody MaterialDTO materialDTO) {
        Material material = new MaterialConcreteBuilder()
                .name(materialDTO.getName())
                .price(materialDTO.getPrice())
                .unit(materialDTO.getUnit())
                .stock(STOCK)
                .build();
        return materialService.createMaterial(material);
    }

    @GetMapping("/{id}")
    public Material getMaterialById(@PathVariable Long id) {
        return materialService.getMaterialById(id);
    }

    @PutMapping("/{id}")
    public Material updateMaterial(@PathVariable Long id, @RequestBody Material updatedMaterial) {
        return materialService.updateMaterial(id, updatedMaterial);
    }

    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
    }

    @GetMapping(value = "/getAllSuppliers")
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

}


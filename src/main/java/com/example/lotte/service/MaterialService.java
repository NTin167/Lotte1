package com.example.lotte.service;

import com.example.lotte.DTO.ItemsDTO;
import com.example.lotte.DTO.MaterialDTO;
import com.example.lotte.model.Material;
import com.example.lotte.repository.MaterialRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;


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

    public void importMaterials(List<ItemsDTO> itemsDTOS, Long supplierId) throws NotFoundException {
        for (ItemsDTO itemsDTO : itemsDTOS) {
            Long itemId = itemsDTO.getItemId();
            Integer quantity = itemsDTO.getQuantity();

            Material material = materialRepository.findById(itemId).orElse(null);
            if (material == null) {
                throw new NotFoundException("Không tìm thấy nguyên liệu với ID: " + itemId);
            }

            // Thực hiện việc cập nhật số lượng nguyên liệu theo số lượng nhập vào
            material.setStock(material.getStock() + quantity);
            // Cập nhật thông tin nhà cung cấp cho nguyên liệu
//            material.setSupplierId(supplierId);

            // Lưu thay đổi vào cơ sở dữ liệu
            materialRepository.save(material);
        }
    }
}

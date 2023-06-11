package com.example.lotte.service;

import com.example.lotte.DTO.FoodCategoryDTO;
import com.example.lotte.DTO.FoodDTO;
import com.example.lotte.DTO.MaterialDTO;
import com.example.lotte.exception.ResourceNotFoundException;
import com.example.lotte.model.Food;
import com.example.lotte.model.FoodCategory;
import com.example.lotte.model.Material;
import com.example.lotte.repository.FoodCategoryRepository;
import com.example.lotte.repository.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private ModelMapper modelMapper; // Dependency: ModelMapper library
    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Food createFood(FoodDTO foodDTO) {
        Food newFood = new Food();
        newFood.setName(foodDTO.getName());
        newFood.setDescription(foodDTO.getDescription());
        newFood.setImage(foodDTO.getImage());
        newFood.setStatus(true);
        newFood.setPrice(foodDTO.getPrice());
        System.out.println(foodDTO.getId());

        List<Material> materials = new ArrayList<>();
        for(MaterialDTO materialDTO : foodDTO.getMaterialDTOS()) {
            Material material = new Material();
            material.setId(materialDTO.getId());
            material.setPrice(materialDTO.getPrice());
            material.setName(materialDTO.getName());
            material.setUnit(materialDTO.getUnit());
            material.setStock(materialDTO.getStock());
            materials.add(material);
        }

        newFood.setMaterials(materials);
        FoodCategory foodCategory = foodCategoryRepository.findById(
                foodDTO.getCategory().getId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm ra Category với id", "ID CATEGORY"));
        newFood.setCategory(foodCategory);
        return foodRepository.save(newFood);
    }

    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }

    public Food updateFood(Long id, FoodDTO updatedFood) {
        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with id: " , id.toString()));
        FoodCategory foodCategory = foodCategoryRepository.findById(updatedFood.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with id: " , id.toString()));
        existingFood.setName(updatedFood.getName());
        existingFood.setDescription(updatedFood.getDescription());
        existingFood.setCategory(foodCategory);
        existingFood.setPrice(updatedFood.getPrice());

        List<Material> materials = new ArrayList<>();
        for(MaterialDTO materialDTO : updatedFood.getMaterialDTOS()) {
            Material material = new Material();
            material.setId(materialDTO.getId());
            material.setPrice(materialDTO.getPrice());
            material.setName(materialDTO.getName());
            material.setUnit(materialDTO.getUnit());
            material.setStock(materialDTO.getStock());
            materials.add(material);
        }

        existingFood.setMaterials(materials);

        return foodRepository.save(existingFood);
    }

    private FoodDTO convertToDto(Food food) {
        FoodDTO foodDTO = modelMapper.map(food, FoodDTO.class);
        foodDTO.setCategory(convertToDto(food.getCategory()));
        return foodDTO;
    }

    private FoodCategoryDTO convertToDto(FoodCategory category) {
        return modelMapper.map(category, FoodCategoryDTO.class);
    }

    public Food updateStatusFood(Long id, Boolean updatedFood) {
        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with id: " , id.toString()));
        existingFood.setStatus(updatedFood);
        return foodRepository.save(existingFood);
    }

    public ResponseEntity<?> getAllFoodCategory() {
        return ResponseEntity.ok(foodCategoryRepository.findAll());
    }

    public ResponseEntity<?> getDetailFood(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with id: " , id.toString()));
        FoodDTO foodDTO = new FoodDTO();
        if(food == null) {
            return ResponseEntity.notFound().build();
        }
        else {

            foodDTO.setId(food.getId());
            foodDTO.setName(food.getName());
            foodDTO.setStatus(food.getStatus());
            foodDTO.setPrice(food.getPrice());
            foodDTO.setImage(food.getImage());
            foodDTO.setDescription(food.getDescription());

            ArrayList<MaterialDTO> materialDTO = new ArrayList<>();
            for(Material material : food.getMaterials()) {
                MaterialDTO materialDTO1 = new MaterialDTO();
                materialDTO1.setId(material.getId());
                materialDTO1.setName(material.getName());
                materialDTO1.setPrice(material.getPrice());
                materialDTO1.setUnit(material.getUnit());
                materialDTO1.setStock(material.getStock());
                materialDTO.add(materialDTO1);
            }


            foodDTO.setCategory(covertToDTO(food.getCategory()));
            foodDTO.setMaterialDTOS(materialDTO);
        }
        return ResponseEntity.ok(foodDTO);
    }

    public FoodCategoryDTO covertToDTO(FoodCategory food) {
        FoodCategoryDTO foodDTO = new FoodCategoryDTO();
        foodDTO.setId(food.getId());
        foodDTO.setName(food.getName());
        return foodDTO;
    }


}

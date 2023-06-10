package com.example.lotte.service;

import com.example.lotte.DTO.FoodCategoryDTO;
import com.example.lotte.DTO.FoodDTO;
import com.example.lotte.exception.ResourceNotFoundException;
import com.example.lotte.model.Food;
import com.example.lotte.model.FoodCategory;
import com.example.lotte.repository.FoodCategoryRepository;
import com.example.lotte.repository.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        Food food = new Food();
        food.setName(foodDTO.getName());
        food.setStatus(foodDTO.getStatus());
        food.setDateUpdate(LocalDateTime.now());
        food.setDescription(foodDTO.getDescription());
        System.out.println(foodDTO.getId());
        FoodCategory foodCategory = foodCategoryRepository.findById(
                foodDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm ra Category với id", "ID CATEGORY"));
        food.setCategory(foodCategory);
        return foodRepository.save(food);
    }

    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }

    public Food updateFood(Long id, Food updatedFood) {
        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with id: " , id.toString()));
        FoodCategory foodCategory = foodCategoryRepository.findById(updatedFood.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with id: " , id.toString()));
        existingFood.setName(updatedFood.getName());
        existingFood.setStatus(updatedFood.getStatus());
        existingFood.setDescription(updatedFood.getDescription());
        existingFood.setDateUpdate(LocalDateTime.now());
        existingFood.setCategory(foodCategory);

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
}

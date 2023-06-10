package com.example.lotte.controller;

import com.example.lotte.DTO.FoodDTO;
import com.example.lotte.model.Food;
import com.example.lotte.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping
    public ResponseEntity<List<Food>> getAllFoods() {
        List<Food> foods = foodService.getAllFoods();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody FoodDTO food) {
        Food createdFood = foodService.createFood(food);
        return ResponseEntity.ok(createdFood);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestBody Food updatedFood) {
        Food food = foodService.updateFood(id, updatedFood);
        return ResponseEntity.ok(updatedFood);
    }
}

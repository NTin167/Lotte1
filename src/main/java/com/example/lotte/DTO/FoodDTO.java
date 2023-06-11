package com.example.lotte.DTO;

import lombok.*;

import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class FoodDTO {
    private Long id;
    private String name;
    private String description;

    private boolean status;
    private FoodCategoryDTO category;

    private ArrayList<MaterialDTO> materialDTOS;
    // Các getter và setter


    public FoodDTO(Long id, String name, String description, boolean status, FoodCategoryDTO category, ArrayList<MaterialDTO> materialDTOS) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.category = category;
        this.materialDTOS = materialDTOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FoodCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(FoodCategoryDTO category) {
        this.category = category;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<MaterialDTO> getMaterialDTOS() {
        return materialDTOS;
    }

    public void setMaterialDTOS(ArrayList<MaterialDTO> materialDTOS) {
        this.materialDTOS = materialDTOS;
    }
}
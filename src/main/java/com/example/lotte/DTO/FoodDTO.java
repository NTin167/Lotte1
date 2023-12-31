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

    private String image;

    private boolean status;

    private Integer price;
    private FoodCategoryDTO category;

    private ArrayList<MaterialDTO> materialDTOS;
    // Các getter và setter


    public FoodDTO() {
    }

    public FoodDTO(Long id, String name, String description, String image, boolean status, Integer price, FoodCategoryDTO category, ArrayList<MaterialDTO> materialDTOS) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.status = status;
        this.price = price;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
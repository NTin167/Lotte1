package com.example.lotte.model;

import javax.persistence.*;
import java.util.List;

@Table(name = "DanhMucMonAn")
@Entity
public class FoodCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Food> foods;

    public FoodCategory() {
    }

    public FoodCategory(Long id, String name, List<Food> foods) {
        this.id = id;
        this.name = name;
        this.foods = foods;
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

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}

package com.example.lotte.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Table(name = "MonAn")
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean status;

    private String description;

    private LocalDateTime dateUpdate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory category;

    public Food() {
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public FoodCategory getCategory() {
        return category;
    }

    public void setCategory(FoodCategory category) {
        this.category = category;
    }

    public Food(Long id, String name, boolean status, String description, LocalDateTime dateUpdate, FoodCategory category) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
        this.dateUpdate = dateUpdate;
        this.category = category;
    }
}

package com.example.lotte.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "NguyenLieu")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer price;

    private String unit;

    private Integer stock;

    @OneToMany(mappedBy = "material", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ReceiptDetail> receiptDetails = new HashSet<>();


    @ManyToMany(mappedBy = "materials")
    private List<Food> foods;
    public Material() {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Set<ReceiptDetail> getReceiptDetails() {
        return receiptDetails;
    }

    public void setReceiptDetails(Set<ReceiptDetail> receiptDetails) {
        this.receiptDetails = receiptDetails;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
    public Material(Long id, String name, Integer price, String unit, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.stock = stock;
    }
    public Material(Long id, String name, Integer price, String unit, Integer stock, Set<ReceiptDetail> receiptDetails, List<Food> foods) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.stock = stock;
        this.receiptDetails = receiptDetails;
        this.foods = foods;
    }

    public static class Builder {
        private Material material;

        public Builder() {
            material = new Material();
        }

        public Builder id(Long id) {
            material.id = id;
            return this;
        }

        public Builder name(String name) {
            material.name = name;
            return this;
        }

        public Builder price(Integer price) {
            material.price = price;
            return this;
        }

        public Builder unit(String unit) {
            material.unit = unit;
            return this;
        }

        public Builder stock(Integer stock) {
            material.stock = stock;
            return this;
        }

        public Material build() {
            return material;
        }
    }
}

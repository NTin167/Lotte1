package com.example.lotte.model;


import lombok.Builder;

import javax.persistence.*;

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

    public Material() {
    }

    public Material(Long id, String name, Integer price, String unit, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.stock = stock;
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

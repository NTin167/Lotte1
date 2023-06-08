package com.example.lotte.builder;

import com.example.lotte.model.Material;

public class MaterialConcreteBuilder implements MaterialBuilder{
    private Long id;
    private String name;
    private int price;
    private String unit;
    private int stock;
    @Override
    public MaterialBuilder id(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public MaterialBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public MaterialBuilder price(int price) {
        this.price = price;
        return this;
    }

    @Override
    public MaterialBuilder unit(String unit) {
        this.unit = unit;
        return this;
    }

    @Override
    public MaterialBuilder stock(int stock) {
        this.stock = stock;
        return this;
    }

    @Override
    public Material build() {
        return new Material(id, name, price, unit, stock);
    }
}

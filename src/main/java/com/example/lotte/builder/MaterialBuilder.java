package com.example.lotte.builder;

import com.example.lotte.model.Material;

public interface MaterialBuilder {
    MaterialBuilder id(Long id);
    MaterialBuilder name(String name);
    MaterialBuilder price(int price);
    MaterialBuilder unit(String unit);
    MaterialBuilder stock(int stock);
    Material build();
}

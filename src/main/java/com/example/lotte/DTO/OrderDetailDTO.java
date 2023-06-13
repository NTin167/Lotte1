package com.example.lotte.DTO;

public class OrderDetailDTO {
    private Long id;
    private int quantity;
    private int price;
    private String foodName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(Long id, int quantity, int price, String foodName) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.foodName = foodName;
    }
}

package com.example.lotte.DTO;

public class OrderDetailDTO {
    private Long id;
    private int quantity;
    private int price;
    private FoodDTO food;

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

    public FoodDTO getFood() {
        return food;
    }

    public void setFood(FoodDTO food) {
        this.food = food;
    }

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(Long id, int quantity, int price, FoodDTO food) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.food = food;
    }
}

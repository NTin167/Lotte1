package com.example.lotte.DTO;

import com.example.lotte.model.Material;
import com.example.lotte.model.Receipt;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReceiptDetailDTO {
    private Long id;

    private Long receipt;

    private String material;

    private int quantity;

    private int status;

    private Integer price;
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReceipt() {
        return receipt;
    }

    public void setReceipt(Long receipt) {
        this.receipt = receipt;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

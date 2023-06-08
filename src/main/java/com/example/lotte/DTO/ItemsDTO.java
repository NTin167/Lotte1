package com.example.lotte.DTO;

public class ItemsDTO {
    private Long itemId;
    private Integer quantity;

    private Long staffId;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public ItemsDTO() {
    }

    public ItemsDTO(Long itemId, Integer quantity, Long staffId) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.staffId = staffId;
    }
}

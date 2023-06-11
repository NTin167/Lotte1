package com.example.lotte.DTO;

import java.util.ArrayList;

public class OrderFoodDTO {
    private ArrayList<ItemsDTO> foodDTOS;

    private Long staffId;

    public ArrayList<ItemsDTO> getFoodDTOS() {
        return foodDTOS;
    }

    public void setFoodDTOS(ArrayList<ItemsDTO> foodDTOS) {
        this.foodDTOS = foodDTOS;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}

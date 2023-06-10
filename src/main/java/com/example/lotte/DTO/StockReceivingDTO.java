package com.example.lotte.DTO;

import java.util.ArrayList;

public class StockReceivingDTO {
    private ArrayList<ItemsDTO> itemsDTOS;
    private Long supplierId;

    private Long staffId;

    public StockReceivingDTO() {
    }

    public StockReceivingDTO(ArrayList<ItemsDTO> itemsDTOS, Long supplierId, Long staffId) {
        this.itemsDTOS = itemsDTOS;
        this.supplierId = supplierId;
        this.staffId = staffId;
    }

    public ArrayList<ItemsDTO> getItemsDTOS() {
        return itemsDTOS;
    }

    public void setItemsDTOS(ArrayList<ItemsDTO> itemsDTOS) {
        this.itemsDTOS = itemsDTOS;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}

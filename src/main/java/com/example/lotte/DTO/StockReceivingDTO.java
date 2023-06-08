package com.example.lotte.DTO;

import java.util.ArrayList;

public class StockReceivingDTO {
    private ArrayList<ItemsDTO> itemsDTOS;
    private Long supplierId;

    public StockReceivingDTO(ArrayList<ItemsDTO> itemsDTOS, Long supplierId) {
        this.itemsDTOS = itemsDTOS;
        this.supplierId = supplierId;
    }

    public StockReceivingDTO() {
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
}

package com.example.lotte.DTO;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

public class ReceiptDTO {
    private Long id;
    private LocalDateTime date;
    private String Employee;
    private String supplier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}

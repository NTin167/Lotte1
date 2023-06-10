package com.example.lotte.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "ChiTietPhieuNhap")
@Entity
public class ReceiptDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private int status;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receiptId", nullable = false)
    @JsonIgnore
    private Receipt receipt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "materialId", nullable = false)
    @JsonIgnore
    private Material material;

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

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

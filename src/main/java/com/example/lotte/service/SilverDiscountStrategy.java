package com.example.lotte.service;

import com.example.lotte.model.Order;

public class SilverDiscountStrategy implements DiscountStrategy {
    private static final double SILVER_DISCOUNT = 0.1; // Giảm giá 10% cho thành viên bạc

    @Override
    public double calculateDiscount(double totalPrice) {
        return totalPrice * SILVER_DISCOUNT;
    }

    @Override
    public int calculatePoints(double totalPrice) {
        return 0;
    }
}
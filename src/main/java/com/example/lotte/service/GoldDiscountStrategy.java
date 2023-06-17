package com.example.lotte.service;

public class GoldDiscountStrategy implements DiscountStrategy {
    private static final double GOLD_DISCOUNT = 0.15; // Giảm giá 15% cho thành viên vàng

    @Override
    public int calculatePoints(double totalPrice) {
        return 0;
    }

    @Override
    public double calculateDiscount(double totalPrice) {
        return totalPrice * GOLD_DISCOUNT;
    }
}
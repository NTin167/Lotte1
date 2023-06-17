package com.example.lotte.service;

public class PlatinumDiscountStrategy implements DiscountStrategy {
    private static final double PLATINUM_DISCOUNT = 0.2; // Giảm giá 20% cho thành viên bạch kim

    @Override
    public int calculatePoints(double totalPrice) {
        return 0;
    }

    @Override
    public double calculateDiscount(double totalPrice) {
        return totalPrice * PLATINUM_DISCOUNT;
    }
}

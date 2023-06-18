package com.example.lotte.service;

public class NoDiscountStrategy implements DiscountStrategy{
    private static final double NO_DISCOUNT = 0; // Giảm giá 10% cho thành viên bạc

    @Override
    public double calculateDiscount(double totalPrice) {
        return totalPrice * NO_DISCOUNT;
    }

    @Override
    public int calculatePoints(double totalPrice) {
        return 0;
    }
}


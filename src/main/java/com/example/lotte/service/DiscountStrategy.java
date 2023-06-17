package com.example.lotte.service;

public interface DiscountStrategy {
    int calculatePoints(double totalPrice);
    double calculateDiscount(double totalPrice);
}

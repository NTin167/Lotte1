package com.example.lotte.service;

import com.example.lotte.model.Order;

public interface DiscountStrategy {
    int calculatePoints(Order order);
    double calculateDiscount(Order order);
}

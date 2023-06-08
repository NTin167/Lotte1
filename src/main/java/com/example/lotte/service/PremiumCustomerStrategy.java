package com.example.lotte.service;

import com.example.lotte.model.Order;

public class PremiumCustomerStrategy implements DiscountStrategy {
    private static final int POINTS_PER_ORDER = 20;
    private static final double DISCOUNT_PERCENTAGE = 0.1;

    @Override
    public int calculatePoints(Order order) {
        // Tính toán điểm tích lũy dựa trên đơn đặt hàng của khách hàng cao cấp
        return order.getOrderDetails().size() * POINTS_PER_ORDER;
    }

    @Override
    public double calculateDiscount(Order order) {
        // Tính toán giảm giá dựa trên tổng giá trị của đơn đặt hàng
        return order.getTotalAmount() * DISCOUNT_PERCENTAGE;
    }
}

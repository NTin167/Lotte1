package com.example.lotte.service;

import com.example.lotte.model.Order;

public class RegularCustomerStrategy implements DiscountStrategy {

    private static final int POINTS_PER_ORDER = 10;
    private static final double DISCOUNT_PERCENTAGE = 0.0;

    @Override
    public int calculatePoints(Order order) {
        // Tính toán điểm tích lũy dựa trên đơn đặt hàng của khách hàng thường
        return order.getOrderDetails().size() * POINTS_PER_ORDER;
    }

    @Override
    public double calculateDiscount(Order order) {
        // Không áp dụng giảm giá cho khách hàng thường
        return 0.0;
    }
}

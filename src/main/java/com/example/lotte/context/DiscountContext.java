package com.example.lotte.context;

import com.example.lotte.service.DiscountStrategy;
import org.springframework.stereotype.Component;

@Component
public class DiscountContext {
    private DiscountStrategy discountStrategy;

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculateDiscount(Integer totalPrice) {
        return discountStrategy.calculateDiscount(totalPrice);
    }


}

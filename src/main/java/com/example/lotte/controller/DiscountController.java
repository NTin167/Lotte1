package com.example.lotte.controller;

import com.example.lotte.context.DiscountContext;
import com.example.lotte.model.Customer;
import com.example.lotte.repository.CustomerRepository;
import com.example.lotte.service.DiscountStrategy;
import com.example.lotte.service.GoldDiscountStrategy;
import com.example.lotte.service.PlatinumDiscountStrategy;
import com.example.lotte.service.SilverDiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {
    @Autowired
    CustomerRepository customerRepository;
    private DiscountContext discountContext;

    public DiscountController(DiscountContext discountContext) {
        this.discountContext = discountContext;
    }
    @PostMapping("/calculate-discount")
    public double calculateDiscount(@RequestParam("totalPrice") int totalPrice, @RequestParam("membership") Long membershipId) {
        DiscountStrategy discountStrategy;
        Customer membership = customerRepository.findById(membershipId).get();
        // Dựa vào hạng thành viên để chọn Strategy phù hợp
        if (membership.getRank().getName().equals("silver")) {
            discountStrategy = new SilverDiscountStrategy();
        } else if (membership.getRank().getName().equals("gold")) {
            discountStrategy = new GoldDiscountStrategy();
        } else if (membership.getRank().getName().equals("platinum")) {
            discountStrategy = new PlatinumDiscountStrategy();
        } else {
            throw new IllegalArgumentException("Invalid membership");
        }

        // Đặt Strategy cho DiscountContext và tính toán số tiền giảm giá
        discountContext.setDiscountStrategy(discountStrategy);
        return discountContext.calculateDiscount(totalPrice);
    }
}

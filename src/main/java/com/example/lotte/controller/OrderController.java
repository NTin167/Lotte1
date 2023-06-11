package com.example.lotte.controller;

import com.example.lotte.DTO.OrderFoodDTO;
import com.example.lotte.DTO.StockReceivingDTO;
import com.example.lotte.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> importMaterials(@RequestBody OrderFoodDTO orderFoodDTO) {
        return orderService.orderFoods(orderFoodDTO);
    }

    @PostMapping(value = "/complete")
    public ResponseEntity<?> completeReceipt(@RequestParam Long orderId) {
        return orderService.completeOrder(orderId);
    }

    @PostMapping(value = "/updateStatus/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable(value = "id") Long id, @RequestParam int status) {
        return orderService.updateOrderStatus(id, status);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable(value = "id") Long id, @RequestBody OrderFoodDTO orderFoodDTO) {
        return orderService.updateOrder(id, orderFoodDTO);
    }
}

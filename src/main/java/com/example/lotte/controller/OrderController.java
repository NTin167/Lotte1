package com.example.lotte.controller;

import com.example.lotte.DTO.BillDTO;
import com.example.lotte.DTO.OrderDetailDTO;
import com.example.lotte.DTO.OrderFoodDTO;
import com.example.lotte.model.OrderDetail;
import com.example.lotte.model.PaymentMethod;
import com.example.lotte.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<?> findAllOrder() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }
    @GetMapping("/{orderId}")
    public List<OrderDetailDTO> getAllOrderDetailsByOrderId(@PathVariable Long orderId) {
        return orderService.getAllOrderDetailsByOrderId(orderId);
    }


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

    @PutMapping(value = "/{id}/updateDetail")
    public ResponseEntity<?> updateOrderDetail(@PathVariable(value = "id") Long id, @RequestBody OrderDetail detail, @RequestParam Long idOrderDetail) {
        return orderService.updateOrderDetailByOrderId(detail, idOrderDetail);
    }

    @PostMapping(value = "{id}/createOrderDetail")
    public ResponseEntity<?> createOrderDetail(@PathVariable(value = "id") Long id, @RequestBody OrderDetail detail) {
        return orderService.createOrderDetailByOrderId(id, detail);
    }

    @DeleteMapping(value = "{id}/deleteOrderDetail")
    public  ResponseEntity<?> deleteOrderDetail(@RequestParam Long detailId) {
        return orderService.deleteOrderDetailByOrderId(detailId);
    }

    @PostMapping(value = "{id}/submitOrder")
    public ResponseEntity<?> submitOrder(@PathVariable(value = "id") Long id, @RequestBody BillDTO billDTO) {
        return orderService.createBill(id, billDTO);
    }

    @GetMapping(value = "{id}/getDiscount")
    public ResponseEntity<?> getDiscount(@PathVariable(value = "id") Long id, @RequestParam Long customerId) {
        return orderService.getDiscount(id, customerId);
    }

    @GetMapping(value = "/getPaymentMethod")
    public List<PaymentMethod> getAllPaymentMethods() {
        return orderService.getAllPaymentMethods();
    }

}

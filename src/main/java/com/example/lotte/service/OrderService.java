package com.example.lotte.service;

import com.example.lotte.model.Customer;
import com.example.lotte.model.Order;
import com.example.lotte.model.OrderDetail;
import com.example.lotte.model.PaymentMethod;
import com.example.lotte.repository.CustomerRepository;
import com.example.lotte.repository.OrderRepository;
import com.example.lotte.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, PaymentMethodRepository paymentMethodRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }


    // Constructor injection

    public Order createOrder(Order order) {
        // Lưu đơn đặt hàng vào cơ sở dữ liệu
        Order savedOrder = orderRepository.save(order);

        // Cập nhật liên kết đơn đặt hàng và chi tiết đơn đặt hàng
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            orderDetail.setOrder(savedOrder);
        }

        // Lưu chi tiết đơn đặt hàng vào cơ sở dữ liệu
        orderRepository.save(savedOrder);

        return savedOrder;
    }

    public void cancelOrder(Long orderId) {
        // Xóa đơn đặt hàng và các chi tiết đơn đặt hàng tương ứng
        orderRepository.deleteById(orderId);
    }

    public Order payOrder(Long orderId, Long customerId, Long paymentMethodId) {
        // Lấy thông tin đơn đặt hàng
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order order = optionalOrder.orElseThrow(() -> new RuntimeException("Order not found"));

        // Lấy thông tin khách hàng
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElseThrow(() -> new RuntimeException("Customer not found"));

        // Lấy thông tin phương thức thanh toán
        Optional<PaymentMethod> optionalPaymentMethod = paymentMethodRepository.findById(paymentMethodId);
        PaymentMethod paymentMethod = optionalPaymentMethod.orElseThrow(() -> new RuntimeException("Payment method not found"));

        // Cập nhật thông tin liên kết
        order.setCustomer(customer);
        order.setPaymentMethod(paymentMethod);

        // Thực hiện thanh toán, lập hóa đơn, cộng/trừ điểm tích lũy, v.v.

        // Lưu các thay đổi vào cơ sở dữ liệu
        return orderRepository.save(order);
    }
}

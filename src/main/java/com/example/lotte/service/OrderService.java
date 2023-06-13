package com.example.lotte.service;

import com.example.lotte.DTO.*;
import com.example.lotte.exception.ResourceNotFoundException;
import com.example.lotte.model.*;
import com.example.lotte.repository.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional
    public ResponseEntity<?> orderFoods(OrderFoodDTO orderFoodDTO) {
        Employee employee = employeeRepository.findById(orderFoodDTO.getStaffId())
                .orElseThrow(()->new ResourceNotFoundException("Employee", "id", orderFoodDTO.getStaffId()));
        Order order = new Order();
        if(employee == null) {
            return ResponseEntity.ok("Không tìm thấy nhân viên với id này");
        }
        else {

            order.setStatus(0);
            order.setEmployee(employee);
            order.setDateOrder(LocalDateTime.now());
            orderRepository.save(order);

            for( ItemsDTO itemsDTO : orderFoodDTO.getFoodDTOS()) {
                Long itemId = itemsDTO.getItemId();
                Integer quantity = itemsDTO.getQuantity();

                Food food = foodRepository.findById(itemId).orElse(null);
                if (food == null) {
                    return ResponseEntity.ok(new NotFoundException("Không tìm thấy nguyên liệu với ID: " + itemId));
                }
                else {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setFood(food);
                    orderDetail.setPrice(food.getPrice());
                    orderDetail.setQuantity(quantity);
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        return ResponseEntity.ok(orderFoodDTO);
    }
    public ResponseEntity<?> completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException("receipt", "id", orderId.toString()));
        order.setStatus(1); // đã hoàn thành
        orderRepository.save(order);
        return ResponseEntity.ok(order);
    }
    public Order createOrder(Order order) {
        // Lưu đơn đặt hàng vào cơ sở dữ liệu
        Order savedOrder = orderRepository.save(order);

        // Cập nhật liên kết đơn đặt hàng và chi tiết đơn đặt hàng
//        for (OrderDetail orderDetail : order.getOrderDetails()) {
//            orderDetail.setOrder(savedOrder);
//        }

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
//        order.setCustomer(customer);
//        order.setPaymentMethod(paymentMethod);

        // Thực hiện thanh toán, lập hóa đơn, cộng/trừ điểm tích lũy, v.v.

        // Lưu các thay đổi vào cơ sở dữ liệu
        return orderRepository.save(order);
    }

    public ResponseEntity<?> updateOrderStatus(Long id, int status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("order", "id", id.toString()));
        order.setStatus(status);
        orderRepository.save(order);
        return ResponseEntity.ok(order);
    }

    public ResponseEntity<?> updateOrder(Long id, OrderFoodDTO orderFoodDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("order", "id", id.toString()));
        if(order == null) {
            return ResponseEntity.notFound().build();
        }
        Employee employee = employeeRepository.findById(orderFoodDTO.getStaffId())
                .orElseThrow(()->new ResourceNotFoundException("Employee", "id", orderFoodDTO.getStaffId()));
        if(employee == null) {
            return ResponseEntity.ok("Không tìm thấy nhân viên với id này");
        }
        else {

            order.setEmployee(employee);
            order.setDateOrder(LocalDateTime.now());
            orderRepository.save(order);

            for( ItemsDTO itemsDTO : orderFoodDTO.getFoodDTOS()) {
                Long itemId = itemsDTO.getItemId();
                Integer quantity = itemsDTO.getQuantity();

                Food food = foodRepository.findById(itemId).orElse(null);
                if (food == null) {
                    return ResponseEntity.ok(new NotFoundException("Không tìm thấy nguyên liệu với ID: " + itemId));
                }
                else {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setFood(food);
                    orderDetail.setPrice(food.getPrice());
                    orderDetail.setQuantity(quantity);
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        return ResponseEntity.ok(orderFoodDTO);
    }

    public List<OrderDTO> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<>();

        for (Order order : orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setStatus(order.getStatus());
            orderDTO.setDateOrder(order.getDateOrder());
            orderDTO.setEmployeeName(order.getEmployee().getName());
            orderDTOs.add(orderDTO);
        }

        return orderDTOs;
    }
    public List<OrderDetailDTO> getAllOrderDetailsByOrderId(Long orderId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrder_Id(orderId);
        List<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();

        for (OrderDetail orderDetail : orderDetails) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setId(orderDetail.getId());
            orderDetailDTO.setQuantity(orderDetail.getQuantity());
            orderDetailDTO.setPrice(orderDetail.getPrice());
            orderDetailDTO.setFoodName(orderDetail.getFood().getName());
            orderDetailDTOs.add(orderDetailDTO);
        }

        return orderDetailDTOs;
    }

    public ResponseEntity<?> updateOrderDetailByOrderId(OrderDetail inputDetail, Long idOrderDetail){
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(idOrderDetail);

        if (optionalOrderDetail.isPresent()) {
            OrderDetail detail = optionalOrderDetail.get();

            // Kiểm tra nếu có yêu cầu sửa trường quantity
            if (inputDetail.getQuantity() > 0) {
                detail.setQuantity(inputDetail.getQuantity());
            }

            // Kiểm tra nếu có yêu cầu sửa trường foodId
            if (inputDetail.getFood().getId() != null) {
                Optional<Food> optionalFood = foodRepository.findById(inputDetail.getFood().getId());

                if (optionalFood.isPresent()) {
                    detail.setFood(optionalFood.get());
                    detail.setPrice(optionalFood.get().getPrice());
                } else {
                    return ResponseEntity.badRequest().body("Food not found with id: " + inputDetail.getFood().getId());
                }
            }

            orderDetailRepository.save(detail);
            return ResponseEntity.ok(detail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> createOrderDetailByOrderId(Long orderId, OrderDetail detail) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        OrderDetail orderDetail;
        if (optionalOrder.isPresent()) {
            Optional<Food> optionalFood = foodRepository.findById(detail.getFood().getId());
            if(optionalFood.isPresent()) {
                orderDetail = new OrderDetail();
                orderDetail.setOrder(optionalOrder.get());
                orderDetail.setFood(optionalFood.get());
                orderDetail.setPrice(optionalFood.get().getPrice());
                orderDetail.setQuantity(detail.getQuantity());
                orderDetailRepository.save(orderDetail);
            }
            else {
                return ResponseEntity.badRequest().body("Food not found with id: " + detail.getFood().getId());
            }
        }
        else {
            return ResponseEntity.badRequest().body("Order not found with id: " + orderId);
        }
        return ResponseEntity.ok(orderDetail);
    }

    public ResponseEntity<?> deleteOrderDetailByOrderId(Long orderDetailId) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(orderDetailId);
        if(optionalOrderDetail.isPresent()) {
            orderDetailRepository.deleteById(orderDetailId);
            return ResponseEntity.ok("Delete succesfully");
        }else {
            return ResponseEntity.badRequest().body("Order detail not found with id: " + orderDetailId);
        }
    }
}

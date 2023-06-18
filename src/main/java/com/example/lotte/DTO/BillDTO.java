package com.example.lotte.DTO;

import java.time.LocalDateTime;

public class BillDTO {
    private Long id;
    private LocalDateTime dateCreate;
    private Integer customerPayment;
    private double discountPayment;
    private CustomerDTO customer;
    private PaymentMethodDTO paymentMethod;
    private OrderDTO order;

    private StaffDTO staff;

    // Constructors, getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getCustomerPayment() {
        return customerPayment;
    }

    public void setCustomerPayment(Integer customerPayment) {
        this.customerPayment = customerPayment;
    }

    public double getDiscountPayment() {
        return discountPayment;
    }

    public void setDiscountPayment(double discountPayment) {
        this.discountPayment = discountPayment;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public StaffDTO getStaff() {
        return staff;
    }

    public void setStaff(StaffDTO staff) {
        this.staff = staff;
    }
}

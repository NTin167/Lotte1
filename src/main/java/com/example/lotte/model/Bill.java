package com.example.lotte.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreate;

    private Integer customerPayment;

    private double discountPayment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Bill(Long id, LocalDateTime dateCreate, Integer customerPayment, double discountPayment, Customer customer, PaymentMethod paymentMethod, Staff staff, Order order) {
        this.id = id;
        this.dateCreate = dateCreate;
        this.customerPayment = customerPayment;
        this.discountPayment = discountPayment;
        this.customer = customer;
        this.paymentMethod = paymentMethod;
        this.staff = staff;
        this.order = order;
    }

    public Bill() {
    }

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

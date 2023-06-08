package com.example.lotte.model;

import javax.persistence.*;

@Entity
@Table(name = "PTTT")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

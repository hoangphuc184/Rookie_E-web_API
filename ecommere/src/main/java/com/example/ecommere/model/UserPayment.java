package com.example.ecommere.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_payment")
public class UserPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_payment_id")
    private Long Id;

    private Long idUser;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "provider")
    private String provider;

    @Column(name = "account_no")
    private Long accountNo;

    @Column(name = "expiry")
    private LocalDate expiry;
}

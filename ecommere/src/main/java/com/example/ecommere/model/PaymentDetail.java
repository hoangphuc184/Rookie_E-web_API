package com.example.ecommere.model;

import com.example.ecommere.enums.EStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_details")
public class PaymentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_detail_id")
    private Long Id;

    @Column(name = "amount")
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
}

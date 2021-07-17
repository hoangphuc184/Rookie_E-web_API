package com.example.ecommere.model;

import com.example.ecommere.enums.ERole;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long Id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 50)
    private ERole role_name;

    public Role(ERole name){
        this.role_name = name;
    }

    public Role() {
    }


}

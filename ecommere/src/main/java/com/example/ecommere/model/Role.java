package com.example.ecommere.model;

import com.example.ecommere.enums.ERole;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "role",
    indexes = {
        @Index(name = "role_idx", columnList = "id, name")
    })
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 50)
    private ERole role_name;

    public Role(ERole name){
        this.role_name = name;
    }
}

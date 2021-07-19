package com.example.ecommere.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts",
    indexes = {
        @Index(name = "cart_idx" , columnList = "id , user_id")
    }
)
@Data
@AllArgsConstructor
public class Carts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "carts", fetch = FetchType.LAZY)
    private List<CartItem> cartItemList = new ArrayList<>();

    @Column(name = "total")
    private Double total;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Carts() {
        this.total = 0D;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.isDeleted = false;
    }
}

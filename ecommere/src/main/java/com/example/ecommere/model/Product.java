package com.example.ecommere.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;


@Entity
@Table(name = "product",
    indexes = {
        @Index(name = "product_idx_1" , columnList = "id, name"),
        @Index(name = "product_idx_2" , columnList = "category_id"),
    }
)
@Data
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String desc;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(	name = "product_image",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private Double price;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "discount_id")
   private Discount discount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Product() {
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.isDeleted = false;
    }
}

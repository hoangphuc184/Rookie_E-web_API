package com.example.ecommere.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private Long Id;

    @Column(name = "url")
    private String url;

    @Column(name = "image_desc")
    private String imageDesc;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}

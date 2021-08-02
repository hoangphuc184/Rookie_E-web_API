package com.example.ecommere.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "image",
    indexes = {
        @Index(name = "image_idx", columnList = "id, url")
    })
@NamedQueries({
        @NamedQuery(name = "Image.findByUrl",
                query = "SELECT i FROM Image i where i.url = :url"),
        @NamedQuery(name = "Image.findListById",
                query = "SELECT i.id FROM Image i")
})
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Image() {
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.isDeleted = false;
    }
}

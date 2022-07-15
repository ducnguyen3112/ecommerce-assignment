package com.nashtech.ecommerce.entity;

import com.nashtech.ecommerce.enums.ProductStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @OneToMany(mappedBy = "product")
    List<OrderItem> oderItems;
    @OneToMany(mappedBy = "product")
    List<CartItem> cartItems;
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "description")
    private String description;
    @Column(name = "inventory")
    private int inventory;
    @Column(name = "price")
    private Long price;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProductStatus status;
    @Column(name = "avg_rating")
    private float avgRating;
    @Column(name = "image")
    private String image;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

}

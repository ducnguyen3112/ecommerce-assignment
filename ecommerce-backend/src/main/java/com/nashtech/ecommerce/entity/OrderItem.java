package com.nashtech.ecommerce.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "oder_items")
public class OrderItem {

    @EmbeddedId
    private OrderItemId orderItemId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private Long price;

    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
}

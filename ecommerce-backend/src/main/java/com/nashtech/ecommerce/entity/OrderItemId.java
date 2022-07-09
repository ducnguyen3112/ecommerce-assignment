package com.nashtech.ecommerce.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class OrderItemId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "product_id")
    private Long productId;
}

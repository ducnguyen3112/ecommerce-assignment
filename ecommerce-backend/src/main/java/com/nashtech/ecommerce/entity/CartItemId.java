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
public class CartItemId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "product_id")
    private Long productId;

}

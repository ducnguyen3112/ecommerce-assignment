package com.nashtech.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

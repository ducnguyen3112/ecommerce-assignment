package com.nashtech.ecommerce.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "cart_items")
public class CartItem {

	@EmbeddedId
	private CartItemId cartItemId;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "modified_at")
	private Date modifiedAt;

	@ManyToOne
	@MapsId("cartId")
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;
}

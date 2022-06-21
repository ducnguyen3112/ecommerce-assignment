package com.nashtech.ecommerce.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "oder_items")
public class OderItem {
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
}

package com.nashtech.ecommerce.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.nashtech.ecommerce.enums.ProductStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class Product {
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
	private Date createdAt;

	@Column(name = "modified_at")
	private Date modifiedAt;
	
	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "status")
	private ProductStatus status;

	@Column(name = "image")
	private String image;

	@OneToMany(mappedBy = "product")
	List<OrderItem> oderItems;
	
	@OneToMany(mappedBy = "product")
	List<CartItem> cartItems;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;

}

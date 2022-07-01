package com.nashtech.ecommerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categories")
public class Category {

	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "description")
	private String description;

	@ManyToOne
	private Category parentCategory;

	@OneToMany(mappedBy = "parentCategory")
	private List<Category> childCategories;

	@OneToMany(mappedBy = "category")
	private List<Product> products;
}

package com.nashtech.ecommerce.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "[users]")
public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "phone_num")
	private String phoneNum;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "registered_at")
	private Date registeredAt;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "status")
	private int status;
	
	@OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<Order> orderList;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Cart> cartList;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
	List<UserRole> userRoles;
	
}
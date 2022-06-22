package com.nashtech.ecommerce.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_role")
public class UserRole {
	@EmbeddedId
	private UserRoleId userRoleId;
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	User user;
	@ManyToOne
	@MapsId("roleId")
	@JoinColumn(name = "role_id")
	Role role;
}

package com.nashtech.ecommerce.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private Set<String> roles;
}

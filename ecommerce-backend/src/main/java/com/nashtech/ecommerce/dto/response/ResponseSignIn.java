package com.nashtech.ecommerce.dto.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class ResponseSignIn {
	private String name;
	private String email;
	private Collection<? extends GrantedAuthority> roles;
	private String token; 
}

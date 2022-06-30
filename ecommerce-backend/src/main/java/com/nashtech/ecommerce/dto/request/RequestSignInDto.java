package com.nashtech.ecommerce.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestSignInDto {
	@Email
	private String email;
	@NotEmpty
	@Min(value = 8)
	private String password;
}

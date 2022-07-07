package com.nashtech.ecommerce.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSignUpDto {
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@Email
	private String email;
	@Pattern(regexp = "\\d*")
	private String phoneNumber;
	@NotEmpty
	private String password;
}

package com.nashtech.ecommerce.dto.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.nashtech.ecommerce.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDto {
	private Long id;
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
	@NotEmpty
	private Set<String> roles;
	private String address;
	private String avatar;
	private String phoneNum;
	private UserStatus status;

}

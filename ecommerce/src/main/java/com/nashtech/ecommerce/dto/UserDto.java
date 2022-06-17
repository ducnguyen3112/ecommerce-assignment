package com.nashtech.ecommerce.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNum;
	private String email;
	private Date registeredAt;
	private String avatar;
	private String address;
}

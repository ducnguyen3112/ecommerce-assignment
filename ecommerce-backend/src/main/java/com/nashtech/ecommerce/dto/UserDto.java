package com.nashtech.ecommerce.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNum;
	private String email;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registeredAt;
	private String avatar;
	private String address;
	public UserDto(Long id) {
		super();
		this.id = id;
	}
	
}
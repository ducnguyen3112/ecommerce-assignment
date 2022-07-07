package com.nashtech.ecommerce.dto.response;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nashtech.ecommerce.enums.UserStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseUserDto {

	private Long id;

	private String firstName;

	private String lastName;

	private String phoneNum;

	private String email;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registeredAt;

	private String avatar;

	private String address;

	private UserStatus status;
	
	private Set<ResponseRoleDto> roles;

}

package com.nashtech.ecommerce.dto.request;

import java.util.Date;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestUserDto {

	private Long id;

	private String firstName;

	private String lastName;

	private String phoneNum;
	@Email
	private String email;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registeredAt;

	private String avatar;

	private String address;

	private int status;

}

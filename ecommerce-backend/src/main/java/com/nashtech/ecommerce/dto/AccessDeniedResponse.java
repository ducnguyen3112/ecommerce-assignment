package com.nashtech.ecommerce.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class AccessDeniedResponse {
	private int status;
	private String message;
	private String error;
	private Date timestamp;
	
}

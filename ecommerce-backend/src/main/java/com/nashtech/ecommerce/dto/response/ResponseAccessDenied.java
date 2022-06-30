package com.nashtech.ecommerce.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class ResponseAccessDenied {
	private int status;
	private String message;
	private String error;
	private Date timestamp;
	
}

package com.nashtech.ecommerce.exception;


import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.nashtech.ecommerce.dto.ResponseMessageDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(
			ResourceNotFoundException ex,WebRequest request){
		ResponseMessageDto errorResponse=new ResponseMessageDto(
				HttpStatus.NOT_FOUND,ex.getMessage(),LocalDateTime.now());
		return new ResponseEntity<Object>(errorResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnwantedException(Exception ex,WebRequest request) {
		ResponseMessageDto errorResponse=new ResponseMessageDto(
				 HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),LocalDateTime.now());
		
		logger.warn(ex.toString());
		return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
}

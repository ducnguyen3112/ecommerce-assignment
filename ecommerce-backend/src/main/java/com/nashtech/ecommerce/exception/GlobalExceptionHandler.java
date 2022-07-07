package com.nashtech.ecommerce.exception;

import java.time.LocalDateTime;

import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nashtech.ecommerce.dto.response.ResponseMessageDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ResponseMessageDto errorResponse = new ResponseMessageDto(HttpStatus.BAD_REQUEST, 
				ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ResponseMessageDto errorResponse = new ResponseMessageDto(HttpStatus.BAD_REQUEST,ex.getFieldError().getField()+" "
				+ex.getAllErrors().get(0).getDefaultMessage(), LocalDateTime.now());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ResponseMessageDto errorResponse = new ResponseMessageDto(HttpStatus.METHOD_NOT_ALLOWED,
				ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(
			ResourceNotFoundException ex, WebRequest request) {
		ResponseMessageDto errorResponse = new ResponseMessageDto(HttpStatus.NOT_FOUND,
				ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public ResponseEntity<Object> handleTypeMismatchException(
			TypeMismatchException ex, WebRequest request) {
		ResponseMessageDto errorResponse = new ResponseMessageDto(HttpStatus.BAD_REQUEST,
				ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUnwantedException(Exception ex) {
		ResponseMessageDto errorResponse = new ResponseMessageDto(
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());

		logger.warn(ex.toString());
		return new ResponseEntity<Object>(errorResponse,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}

package com.learnrestservice.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customDetail = new CustomErrorDetails("From MethodArgumentNotValid Exception in GEH",
				ex.getMessage(), new Date());

		return new ResponseEntity<>(customDetail, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetail = new CustomErrorDetails(
				"From HttpRequestMethodNotSupportedException Exception in GEH, Method Not allowed", ex.getMessage(),
				new Date());

		return new ResponseEntity<>(customErrorDetail, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex,
			WebRequest request) {
		CustomErrorDetails customErrorDetail = new CustomErrorDetails(ex.getMessage(), request.getDescription(false),
				new Date());

		return new ResponseEntity<>(customErrorDetail, HttpStatus.NOT_FOUND);
	}

	// ConstraintViolationException
	// Validate PathVariable from Controller
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {

		CustomErrorDetails customErrorDetail = new CustomErrorDetails(ex.getMessage(), request.getDescription(false),
				new Date());

		return new ResponseEntity<>(customErrorDetail, HttpStatus.BAD_REQUEST);
	}

}

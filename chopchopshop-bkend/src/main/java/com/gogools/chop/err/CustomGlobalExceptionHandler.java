package com.gogools.chop.err;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gogools.rest.RespEntityCreator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// error handle for @Valid
	@SuppressWarnings("unchecked")
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {	
		
		// Get all errors
		Map<String, List<String>> errors = ex.getBindingResult()
												.getFieldErrors()
												.stream()
												.collect(Collectors.groupingBy(FieldError::getField,
																			   Collectors.mapping( s -> s.getDefaultMessage(),
																					   Collectors.collectingAndThen(Collectors.toSet(), ArrayList::new))));
		
		log.error("==ERROR @Valid on \"{}\".\"{}\" httpStatus: \"{}\", errors: {}", ((ServletWebRequest)request).getRequest().getRequestURI(),
																					 ((ServletWebRequest)request).getRequest().getMethod(),
																					 status, errors);
		
		//ResponseEntity.status(status).headers(headers).body(new RestResponse<Map<String, String>>("Validation error", errors));

		return (ResponseEntity<Object>) new RespEntityCreator<Object>("Validation error", errors).bad(headers);
	}

}
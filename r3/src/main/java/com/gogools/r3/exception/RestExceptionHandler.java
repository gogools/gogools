package com.gogools.r3.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gogools.r3.control.FeederResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<Object> handleEntityNotFound(IllegalArgumentException ex) {
		
		return buildResponseEntity(FeederResponse.createFailureResponse(ex.getMessage()));
	}

	private ResponseEntity<Object> buildResponseEntity(FeederResponse resp) {

		return new ResponseEntity<>(resp, resp.getStatus());
	}
}

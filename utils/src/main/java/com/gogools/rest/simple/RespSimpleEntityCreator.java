package com.gogools.rest.simple;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RespSimpleEntityCreator<T> {

	private RestResponseSimpleDTO<T> response;

	public RespSimpleEntityCreator(String message) {

		response = new RestResponseSimpleDTO<T>(message);
	}

	public RespSimpleEntityCreator(String message, T body) {

		response = new RestResponseSimpleDTO<T>(message, body);
	}

	public ResponseEntity<?> ok() {

		return ResponseEntity.ok(response);
	}

	public ResponseEntity<?> created() {

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<?> bad() {

		return ResponseEntity.badRequest().body(response);
	}

	public ResponseEntity<?> err() {

		return ResponseEntity.badRequest().body(response);
	}

	public ResponseEntity<?> notFound() {

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> unauthorized() {

		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
}

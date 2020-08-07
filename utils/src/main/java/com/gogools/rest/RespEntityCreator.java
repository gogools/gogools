package com.gogools.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RespEntityCreator<T> {

	private RestResponseDTO<T> response;

	public RespEntityCreator(String message) {

		response = new RestResponseDTO<T>(message);
	}

	public RespEntityCreator(String message, T body) {

		response = new RestResponseDTO<T>(message, body);
	}

	public RespEntityCreator(String message, Map<String, List<String>> errors) {

		response = new RestResponseDTO<T>(message, errors);
	}

	public RespEntityCreator(String message, T body, Map<String, List<String>> errors) {

		response = new RestResponseDTO<T>(message, body, errors);
	}

	public ResponseEntity<?> ok() {

		return ResponseEntity.ok(response);
	}

	public ResponseEntity<?> ok(HttpHeaders headers) {

		return ResponseEntity.ok().headers(headers).body(response);
	}

	public ResponseEntity<?> created() {

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<?> created(HttpHeaders headers) {

		return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
	}

	public ResponseEntity<?> bad() {

		return ResponseEntity.badRequest().body(response);
	}

	public ResponseEntity<?> bad(HttpHeaders headers) {

		return ResponseEntity.badRequest().headers(headers).body(response);
	}

	public ResponseEntity<?> err() {

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<?> err(HttpHeaders headers) {

		return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<?> notFound() {

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> unauthorized() {

		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
	public ResponseEntity<?> unauthorized(HttpHeaders headers) {

		return new ResponseEntity<>(response, headers, HttpStatus.UNAUTHORIZED);
	}
}

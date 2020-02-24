package com.gogools.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RespEntityCreator<T> {

	private RestResponse<T> response;

	public RespEntityCreator(String message) {

		response = new RestResponse<T>(message);
	}

	public RespEntityCreator(String message, T body) {

		response = new RestResponse<T>(message, body);
	}

	public RespEntityCreator(String message, Map<String, List<String>> errors) {

		response = new RestResponse<T>(message, errors);
	}

	public RespEntityCreator(String message, T body, Map<String, List<String>> errors) {

		response = new RestResponse<T>(message, body, errors);
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

	public ResponseEntity<Object> badHandler(HttpHeaders headers) {

		return ResponseEntity.badRequest().headers(headers).body(response);
	}

	public ResponseEntity<?> err() {

		return ResponseEntity.badRequest().body(response);
	}

	public ResponseEntity<?> err(HttpHeaders headers) {

		return ResponseEntity.badRequest().headers(headers).body(response);
	}

}

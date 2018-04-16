package com.gogools.r3.control;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class FeederResponse {

	private final Integer	 	idResponse;
	private final String	 		description;
	private final String	 		message;
	private final HttpStatus 	status;

	public static FeederResponse createSucessResponse(String msg) {

		return new FeederResponse(0, "Success", msg, HttpStatus.OK);
	}

	public static FeederResponse createFailureResponse(String msg) {

		return new FeederResponse(-1, "Failure", msg, HttpStatus.BAD_REQUEST);
	}
}

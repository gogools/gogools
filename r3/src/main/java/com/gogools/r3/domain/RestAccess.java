package com.gogools.r3.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class RestAccess {

	@Id
	private String			id;
	private final String		username;
	private final String		password;

}

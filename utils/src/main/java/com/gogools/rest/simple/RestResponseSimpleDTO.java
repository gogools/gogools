package com.gogools.rest.simple;

import java.util.Date;

import lombok.Data;

@Data
public class RestResponseSimpleDTO<T> {

	private Date	date;
	
	private String	msg;

	private T		data;

	public RestResponseSimpleDTO() {

		this.date = new Date();
		this.msg  = "";
		this.data = null;
	}

	public RestResponseSimpleDTO(String msg) {

		this.date = new Date();
		this.msg  = msg;
		this.data = null;
	}

	public RestResponseSimpleDTO(String msg, T data) {

		this.date = new Date();
		this.msg  = msg;
		this.data = data;
	}

}

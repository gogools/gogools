package com.gogools.sb.service.mongo;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Domain {

	@Id
	private String 	id;
	private Date	createDate;
	private Date	modifyDate;
	private Boolean	active;

	public Domain() {

		createDate = new Date();
		modifyDate = null;
		active = true;
	}
}

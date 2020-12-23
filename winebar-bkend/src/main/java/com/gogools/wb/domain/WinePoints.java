package com.gogools.wb.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class WinePoints {

	private String user;
	private Double qty;
}

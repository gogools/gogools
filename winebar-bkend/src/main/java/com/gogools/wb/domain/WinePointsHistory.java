package com.gogools.wb.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class WinePointsHistory {
	
	@Id
	private String id;
	private String user;
	private String bar;
	private Date   opDate;
	private String op;

}

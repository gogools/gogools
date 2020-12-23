package com.gogools.domain;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "comment")
public class Comment {
	
	@Id
	private String			id;

	@NotEmpty
	String comment;
	
	@DBRef
	User user;
}

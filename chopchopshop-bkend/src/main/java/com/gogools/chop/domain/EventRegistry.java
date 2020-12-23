package com.gogools.chop.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gogools.sb.service.mongo.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection = "eventRegister")
@EqualsAndHashCode(callSuper = false)
public class EventRegistry extends Domain {
	
	@NotEmpty
	@NotNull
	private String	name;
	
	@Indexed(unique = true)
	private String  eventCode;

	@NotEmpty
	@NotNull
	private String	lastname;

	@NotEmpty
	@NotNull
	private String	mothersname;

	@NotEmpty
	@NotNull
	private String	email;

	@NotEmpty
	@NotNull
	private String	phone;

	@NotEmpty
	@NotNull
	private String	origin;

	@NotEmpty
	@NotNull
	private String	eventDesc;
	
}

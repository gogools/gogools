package com.gogools.chop.dto;

import com.gogools.sb.service.mongo.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EventRegistryDTO extends Domain {
	
	private String	name;
	private String  eventCode;
	private String	lastname;
	private String	mothersname;
	private String	email;
	private String	phone;
	private String	origin;
	private String	eventDesc;

}

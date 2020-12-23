package com.gogools.chop.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gogools.sb.service.mongo.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection = "brand")
@EqualsAndHashCode(callSuper = false)
public class Brand extends Domain {

	@NotNull
	@NotEmpty
	@Indexed(unique = true)
	private String	name;
	
	@NotNull
	@NotEmpty
	private String	excelSheetName;

	@NotNull
	@NotEmpty
	private String	descrip;
	
	private String	imgName;

	@NotNull
	@NotEmpty
	private String	displayName;

}

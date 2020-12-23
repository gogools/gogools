package com.gogools.chop.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gogools.sb.service.mongo.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection = "product")
@EqualsAndHashCode(callSuper = false)
public class Product extends Domain {

	@Indexed
	private String	brandId;

	@Indexed(unique = true)
	private String	code;

	@Indexed
	private String	name;

	private String	descrip;
	private String	imgName;
	private Double	price;
	private Integer	qty;

	@Indexed
	private String	type;
}

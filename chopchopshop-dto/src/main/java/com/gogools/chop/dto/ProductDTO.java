package com.gogools.chop.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.gogools.sb.service.mongo.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductDTO extends Domain {
	
	private String	id;
	
	@NotNull
	@NotEmpty
	private String	brandId;
	
	@NotNull
	@NotEmpty
	private String	code;
	
	@NotNull
	@NotEmpty
	private String	name;
	
	@NotNull
	@NotEmpty
	private String	descrip;
	private String	imgName;
	
	@NotNull
	@Positive
	private Double	price;
	
	@NotNull
	@PositiveOrZero
	private Integer	qty;
	
	@NotNull
	@NotEmpty
	private String	type;
	
	private Date	createDate;
	private Date	modifyDate;
	private Boolean	active;
}

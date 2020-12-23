package com.gogools.chop.dto;

import com.gogools.sb.service.mongo.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BrandDTO extends Domain {

	private String	name;
	private String	excelSheetName;
	private String	descrip;
	private String	imgName;
	private String	displayName;

}

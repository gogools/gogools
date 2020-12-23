package com.app.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document
@EqualsAndHashCode(callSuper = false)
public class Doc {

	final private String name;
	final private String path;
}

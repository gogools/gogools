package com.app.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Bitacora {

	private Date	f_creacion;
	private Date	f_modificacion;
	private Boolean	active;
}

package com.app.forms;

import lombok.Data;

@Data
public abstract class UserForm {

	private String	nombre;
	private String	ap_p;
	private String	ap_m;
	private String	email;
	private String	pass;

}

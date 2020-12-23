package com.app.forms;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AlumnoForm extends UserForm {

	private String	boleta;

}

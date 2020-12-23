package com.gogools.chop.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EventForm {

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 50)
	private String	name;

	@NotNull
	@NotEmpty
	@Size(min = 5, max = 50)
	private String	lastname;

	@NotNull
	@NotEmpty
	@Size(min = 5, max = 50)
	private String	mothersname;

	@NotNull
	@NotEmpty
	@Email
	@Size(min = 5, max = 50)
	private String	email;

	@NotNull
	@NotEmpty
	private String	phone;

	@NotNull
	@NotEmpty
	private String	origin;

	@NotNull
	@NotEmpty
	private String	eventDesc;
}

package com.app.domain;

import com.gogools.enums.DaysOfWeekEnum;

import lombok.Data;

@Data
public class Horario {

	private DaysOfWeekEnum	day;
	private String			hora;
}

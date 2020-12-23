package com.app.domain;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Clase extends Bitacora {

	private Materia			materia;
	private List<Alumno>	alumnos;
}

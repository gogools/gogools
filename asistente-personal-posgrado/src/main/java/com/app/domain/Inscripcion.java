package com.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Inscripcion extends Bitacora {

	final private Maestria	maestria;
	final private Semestre	semestre;
	final private Usuario	alumno;

	private Doc				documentos;
	private Materia			materia;
	private Float			calificacion;
	private String			sello;

}

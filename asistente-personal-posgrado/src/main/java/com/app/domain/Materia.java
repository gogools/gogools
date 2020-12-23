package com.app.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document
@EqualsAndHashCode(callSuper = false)
public class Materia extends Bitacora {

	@Id
	final private String	id;

	private String			nombre;
	private String			clave;

	private Semestre		semestre;
	private List<Horario>	horario;
}

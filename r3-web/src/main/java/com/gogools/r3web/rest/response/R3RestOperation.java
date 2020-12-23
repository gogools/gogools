package com.gogools.r3web.rest.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class R3RestOperation {

	private String	idClient;
	private String	idOperation;
	private String	idCard;

	// variable
	private String	idMonedero;
	private String	tubos;
	private String	codigoTeclado;
	private String	cofre;
	private String	monedasEntrada;
	private String	monedasSalida;
	private String	uid;
	private String	adicionales;
	private String	debugData;
	// end-variable

	private String	tipoGabinete;
	private String	cardDate;
	private Date		sysDate;
	private String	metodoReporte;

}

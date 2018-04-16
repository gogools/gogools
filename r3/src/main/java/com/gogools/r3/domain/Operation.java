package com.gogools.r3.domain;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.gogools.utils.DateUtil;

import lombok.Data;

@Data
public class Operation {

	@Id
	private BigInteger	id;

	@Indexed
	private String		idClient;
	

	@Indexed
	private String		idCard;

	@Indexed
	private String		idOperation;

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

	private String	sysDateFormatted;

	private String	metodoReporte;
	
	
	public Operation() {
		
		this.sysDate = DateUtil.getDateFromString(DateUtil.getCurrentMexDateTime(), DateUtil.DEFAULT_DATE_TIME_FORMAT);
	}
	

	public Operation(String	idClient, String	 idOperation, String idCard) {
		
		this.idClient 		= idClient;
		this.idOperation 	= idOperation;
		this.idCard 			= idCard;
		this.sysDate 		= DateUtil.getDateFromString(DateUtil.getCurrentMexDateTime(), DateUtil.DEFAULT_DATE_TIME_FORMAT);
	}

	public String getSysDateFormatted() {

		sysDateFormatted = DateUtil.getStringFromDate(this.sysDate, DateUtil.DEFAULT_DATE_TIME_FORMAT);
		return sysDateFormatted;
	}
}

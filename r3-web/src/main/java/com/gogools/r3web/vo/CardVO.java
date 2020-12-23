package com.gogools.r3web.vo;

import java.util.Date;

import com.gogools.r3web.domain.Card;

import lombok.Data;

@Data
public class CardVO {

	private Integer	serial;
	private Integer	cardType;
	private String 	name;
	private boolean	isActive;
	private Date		createDate;
	private Date		inactiveDate;
	private Float	latitude;
	private Float	longitude;
	private String	desc;
	
	public CardVO(Card card) {
		
		this.serial 			= card.getSerial();
		this.cardType 		= card.getCardtype();
		this.name 			= card.getName();
		this.isActive 		= card.isIsactive();
		this.createDate 		= card.getCreatedate();
		this.inactiveDate 	= card.getInactivedate();
		this.latitude 		= card.getLatitude();
		this.longitude 		= card.getLongitude();
		this.desc 			= card.getDesc();
	}
}

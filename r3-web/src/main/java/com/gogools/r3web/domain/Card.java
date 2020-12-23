package com.gogools.r3web.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "card")
public class Card {

	private Integer	serial;

	private Integer	cardtype;
	private String 	name;
	private boolean	isactive;
	private Date		createdate;
	private Date		inactivedate;
	private Float	latitude;
	private Float	longitude;
	private String	desc;

	private Client	client;
	
	
	public Card() {
		
		isactive = true;
		createdate = new Date();
		latitude = 0f;
		longitude = 0f;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}
	
	@NotNull
	@Size(max = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	public Integer getCardtype() {
		return cardtype;
	}

	public void setCardtype(Integer cardtype) {
		this.cardtype = cardtype;
	}

	@NotNull
	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean iscative) {
		this.isactive = iscative;
	}

	@NotNull
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getInactivedate() {
		return inactivedate;
	}

	public void setInactivedate(Date inactivedate) {
		this.inactivedate = inactivedate;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	@NotNull
	@Size(max = 150)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idclient")
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}

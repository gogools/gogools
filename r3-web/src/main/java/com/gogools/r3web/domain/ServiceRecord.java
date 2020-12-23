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

@Entity
@Table(name = "service_record")
public class ServiceRecord {

	private Integer	id;
	private Date		initdate;
	private Date		enddate;
	private Integer	status;
	private boolean	isative;
	
	private Service 	service;
	private Client 	client;
	
	public ServiceRecord() {
		
		isative = true;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull
	public Date getInitdate() {
		return initdate;
	}

	public void setInitdate(Date initdate) {
		this.initdate = initdate;
	}

	@NotNull
	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@NotNull
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@NotNull
	public boolean isIsative() {
		return isative;
	}

	public void setIsative(boolean isative) {
		this.isative = isative;
	}

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idservice")
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
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

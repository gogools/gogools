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
@Table(name = "report")
public class Report {

	private Integer	idreport;
	private String	rules;
	private Date		createdate;

	private Client	client;
	
	public Report() {
		
		createdate = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getIdreport() {
		return idreport;
	}

	public void setIdreport(Integer idreport) {
		this.idreport = idreport;
	}

	@NotNull
	@Size(max = 300)
	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}
	
	@NotNull
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idclient")
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}

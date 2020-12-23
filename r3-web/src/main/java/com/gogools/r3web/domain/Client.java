package com.gogools.r3web.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "client")
public class Client {

	private Long					idclient;
	private String				name;
	private String				email;
	private String				logo;
	private boolean				isactive;
	private Date					createdate;
	private Date					inactivedate;
	private String				css;

	private List<User>			users;
	private Service				service;
	private List<Card>			cards	= new ArrayList<Card>();
	private List<Report>			reports	= new ArrayList<Report>();
	private List<ServiceRecord>	records = new ArrayList<ServiceRecord>();

	public Client() {

		isactive = true;
		createdate = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIdclient() {
		return idclient;
	}

	public void setIdclient(Long idclient) {
		this.idclient = idclient;
	}

	@NotNull
	@Size(max = 45)
	@Column(unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@Size(max = 45)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Size(max = 45)
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@NotNull
	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
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

	@Size(max = 45)
	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true, fetch = FetchType.LAZY)
	public List<User> getUser() {
		return users;
	}

	public void setUser(List<User> users) {
		this.users = users;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idservice")
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true, fetch = FetchType.LAZY)
	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true, fetch = FetchType.LAZY)
	public List<ServiceRecord> getRecords() {
		return records;
	}

	public void setRecords(List<ServiceRecord> records) {
		this.records = records;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true, fetch = FetchType.LAZY)
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

}

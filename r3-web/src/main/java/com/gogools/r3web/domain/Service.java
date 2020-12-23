package com.gogools.r3web.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "service")
public class Service {

	private Long					idservice;

	private String				name;
	private String				desc;
	private Float				price;

	private List<Client>			clients	= new ArrayList<Client>();
	private List<ServiceRecord>	records	= new ArrayList<ServiceRecord>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIdservice() {
		return idservice;
	}

	public void setIdservice(Long idservice) {
		this.idservice = idservice;
	}

	@NotNull
	@Size(max = 45)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@Size(max = 150)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@NotNull
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float prices) {
		this.price = prices;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "service", orphanRemoval = true)
	public List<ServiceRecord> getRecords() {
		return records;
	}

	public void setRecords(List<ServiceRecord> records) {
		this.records = records;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "service", fetch = FetchType.LAZY)
	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

}

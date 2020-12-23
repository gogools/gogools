package com.gogools.r3web.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

	private Long	  	  	iduser;

	private String	  	username;
	private String	  	password;
	private String	  	email;
	private Date	  	  	lastlogin;
	private boolean	  	isactive;
	private Date	  	  	createdate;
	private Date	  	  	inactivedate;
	private Integer	  	loginfails;
	private String	  	secret;
	private String	  	secretanswer;
	private String	  	recoverytoken;
	private Client	  	client;
	private Set<Role> 	roles;
	
	public User() {
		
		createdate = new Date();
		isactive = false;
		loginfails = 0;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIduser() {
		return iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}

	@NotNull
	@Size(max = 45)
	@Column(unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotNull
	@Size(max = 150)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotNull
	@Size(max = 45)
	@Column(unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
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

	@NotNull
	public Integer getLoginfails() {
		return loginfails;
	}

	public void setLoginfails(Integer loginfails) {
		this.loginfails = loginfails;
	}

	@NotNull
	@Size(max = 45)
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@NotNull
	@Size(max = 45)
	public String getSecretanswer() {
		return secretanswer;
	}

	public void setSecretanswer(String secretanswer) {
		this.secretanswer = secretanswer;
	}

	@Size(max = 45)
	public String getRecoverytoken() {
		return recoverytoken;
	}

	public void setRecoverytoken(String recoverytoken) {
		this.recoverytoken = recoverytoken;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_iduser"), inverseJoinColumns = @JoinColumn(name = "role_idrole"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idclient")
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}

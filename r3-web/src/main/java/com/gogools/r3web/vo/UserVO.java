package com.gogools.r3web.vo;

import java.util.Date;

import com.gogools.r3web.domain.User;

import lombok.Data;

@Data
public class UserVO {

	private Long	  	  	idUser;
	private String	  	username;
	private String	  	password;
	private String	  	passwordConfirm;
	private String	  	email;
	private Date	  	  	lastLogin;
	private boolean	  	isActive;
	private Date	  	  	createDate;
	private Date	  	  	inactiveDate;
	private Integer	  	loginFails;
	private String	  	secret;
	private String	  	secretAnswer;
	private String	  	recoveryToken;
	
	
	public UserVO(User user) {
		
		this.idUser 			= user.getIduser();
		this.username 		= user.getUsername();
		this.password 		= user.getPassword();
		this.email 			= user.getEmail();
		this.lastLogin 		= user.getLastlogin();
		this.isActive 		= user.isIsactive();
		this.createDate 		= user.getCreatedate();
		this.inactiveDate 	= user.getInactivedate();
		this.loginFails 		= user.getLoginfails();
		this.secret 			= user.getSecret();
		this.secretAnswer 	= user.getSecretanswer();
		this.recoveryToken 	= user.getRecoverytoken();
	}
}

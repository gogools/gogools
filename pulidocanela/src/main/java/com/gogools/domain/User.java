package com.gogools.domain;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gogools.enums.RoleEnum;
import com.gogools.util.Util;

import lombok.Data;

@Data
@Document(collection = "user")
public class User {

	@Id
	private String			id;

	@NotEmpty
	private String			name;

	@NotEmpty
	private String			email;

	@NotEmpty
	private String			password;

	@NotNull
	private RoleEnum		role;

	private ZonedDateTime	createDate;
	private ZonedDateTime	modifyDate;

	public User() {

		this.role  = RoleEnum.NORMAL_ROLE;
		createDate = ZonedDateTime.now(ZoneOffset.UTC);
		modifyDate = null;
	}

	
	public User(String name, String email, RoleEnum role) {

		this.name 		= name;
		this.email 		= email;
		this.role 		= role;
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password   = encoder.encode( Util.getPassword(email) );

		createDate 		= ZonedDateTime.now(ZoneOffset.UTC);
		modifyDate 		= null;
	}
	
	
	public void setPassword( String password ) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password   = encoder.encode( Util.getPassword(password) );
	}
	
	
	public void setEmail( String email ) {
		
		this.email = email;
		
		if( this.password == null ) {
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			this.password   = encoder.encode( Util.getPassword(email) );
		}
	}
}

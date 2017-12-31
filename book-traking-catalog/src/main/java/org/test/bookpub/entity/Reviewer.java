package org.test.bookpub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Reviewer {

	@Id
	@GeneratedValue
	private Long		 id;

	final private String firstName;
	final private String lastName;

}
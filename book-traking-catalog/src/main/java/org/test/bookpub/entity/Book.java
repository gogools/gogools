package org.test.bookpub.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Book {

	@Id
	@GeneratedValue
	private Long				  id;
	final private String		  isbn;
	final private String		  title;
	private String				  description;

	@ManyToOne
	final private Author		  author;

	@ManyToOne
	final private Publisher		  publisher;

	@ManyToMany
	final private List<Reviewers> reviewers;
	
}
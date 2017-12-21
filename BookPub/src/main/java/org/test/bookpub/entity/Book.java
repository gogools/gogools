package org.test.bookpub.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private Long			id;
	private String			isbn;
	private String			title;
	private String			description;

	@ManyToOne
	private Author			author;

	@ManyToOne
	private Publisher		publisher;

	@ManyToMany
	private List<Reviewers>	reviewers;

	protected Book() {
	}

	public Book(String isbn, String title, Author author, Publisher publisher) {

		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}
}
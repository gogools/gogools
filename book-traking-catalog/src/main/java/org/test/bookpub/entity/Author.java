package org.test.bookpub.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Author {

	@Id
	@GeneratedValue
	private Long		 id;
	final private String firstName;
	final private String lastName;

	@OneToMany(mappedBy = "author")
	private List<Book>	 books;

}
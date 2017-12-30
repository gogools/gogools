package org.test.bookpub.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Publisher {

	@Id
	@GeneratedValue
	private Long	   id;
	
	final private String	   name;
	
	@OneToMany(mappedBy = "publisher")	
	private List<Book> books;

}

package com.gogools.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gogools.domain.User;


public interface UserRepository extends MongoRepository<User, String> {

	
	public User findByName(String name);
	
	public User findByEmail(String email);
	
	public List<User> findAllByOrderByNameAsc();
}

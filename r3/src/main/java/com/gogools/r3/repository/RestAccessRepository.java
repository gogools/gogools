package com.gogools.r3.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gogools.r3.domain.RestAccess;

public interface RestAccessRepository extends MongoRepository<RestAccess, String> {

	public RestAccess findByUsername(String username);
}

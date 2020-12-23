package com.gogools.chop.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gogools.chop.domain.EventRegistry;

public interface EventRegistryRepo extends MongoRepository<EventRegistry, String>{

}

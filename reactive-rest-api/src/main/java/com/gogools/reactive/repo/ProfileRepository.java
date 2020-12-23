package com.gogools.reactive.repo;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.gogools.reactive.domain.Profile;

public interface ProfileRepository extends ReactiveMongoRepository<Profile, String> {
	
}

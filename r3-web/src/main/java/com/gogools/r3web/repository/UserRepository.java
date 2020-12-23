package com.gogools.r3web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gogools.r3web.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername(String username);
}

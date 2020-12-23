package com.gogools.r3web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gogools.r3web.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
}

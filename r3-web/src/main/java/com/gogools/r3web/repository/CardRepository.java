package com.gogools.r3web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gogools.r3web.domain.Card;
import com.gogools.r3web.domain.Client;

public interface CardRepository extends JpaRepository<Card, Long> {
	
	List<Card> findByClient(Client client);
}

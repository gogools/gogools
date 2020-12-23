package com.gogools.r3web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogools.r3web.domain.Card;
import com.gogools.r3web.domain.Client;
import com.gogools.r3web.repository.CardRepository;

@Service
public class R3CommandCenterService {

	@Autowired
	private CardRepository cardRepo;
	
	public List<Card> getCardCatalog(Long idClient) {
		
		Client client = new Client();
		client.setIdclient(idClient);
		
		List<Card> result = cardRepo.findByClient(client);
		
		if(!result.isEmpty()) {
			
			return result;
			
		} else {
			
			return null;
		}
	}
}

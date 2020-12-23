package com.gogools.chop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogools.chop.domain.EventRegistry;
import com.gogools.chop.repo.EventRegistryRepo;

@Service
public class EventRegistryService implements IEventRegistryService {

	@Autowired
	private EventRegistryRepo repo;
	
	@Override
	public EventRegistry saveEventRegistry(EventRegistry event) {
		
		return repo.save(event);
	}

	@Override
	public EventRegistry updateEventRegistry(EventRegistry event) {
		
		if( event.getId() == null || event.getId().isEmpty() ) {
			
			return null;
		}
		
		Optional<EventRegistry> eventBd = repo.findById( event.getId() );
		if( eventBd.isPresent() ) {
			
			event.setId( eventBd.get().getId() );
		}
		else {
			
			return null;
		}
		
		return repo.save(event);	
	}

	@Override
	public List<EventRegistry> saveEventRegistries(List<EventRegistry> events) {
		
		return repo.saveAll(events);
	}

	@Override
	public EventRegistry getEventRegistryById(String eventId) {
		
		return repo.findById(eventId).orElse(null);
	}

	@Override
	public List<EventRegistry> getAllEventRegistries() {
		
		return repo.findAll();
	}

	@Override
	public Long getCount() {
		
		return repo.count();
	}

}
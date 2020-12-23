package com.gogools.chop.services;

import java.util.List;

import com.gogools.chop.domain.EventRegistry;

public interface IEventRegistryService {

	public EventRegistry saveEventRegistry( EventRegistry event );
	
	public EventRegistry updateEventRegistry( EventRegistry event );
	
	public List<EventRegistry> saveEventRegistries( List<EventRegistry> events );
	
	public EventRegistry getEventRegistryById( String eventId );
	
	public List<EventRegistry> getAllEventRegistries();
	
	public Long getCount();
}

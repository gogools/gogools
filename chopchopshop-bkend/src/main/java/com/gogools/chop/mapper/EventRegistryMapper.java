package com.gogools.chop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gogools.chop.domain.EventRegistry;
import com.gogools.chop.dto.EventRegistryDTO;

@Mapper(componentModel = "spring")
public interface EventRegistryMapper { 

	EventRegistryMapper MAPPER = Mappers.getMapper( EventRegistryMapper.class );
	
	EventRegistryDTO 	eventr2eventrDTO( EventRegistry event );
	
	EventRegistry		eventrDTO2eventr( EventRegistryDTO event );
}

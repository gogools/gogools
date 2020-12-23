package com.gogools.chop.service;

import java.util.List;

import com.gogools.chop.dto.EventRegistryDTO;
import com.gogools.chop.dto.ProductDTO;
import com.gogools.rest.RestResponseDTO;

public interface IChopBkendClientSrvc {

	public RestResponseDTO<List<ProductDTO>> getAllProducts();
	
	public RestResponseDTO<EventRegistryDTO> createEventRegistry(EventRegistryDTO er); 
}

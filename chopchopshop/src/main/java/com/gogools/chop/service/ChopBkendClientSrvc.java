package com.gogools.chop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.gogools.chop.dto.EventRegistryDTO;
import com.gogools.chop.dto.ProductDTO;
import com.gogools.enums.Emj;
import com.gogools.rest.RestClient;
import com.gogools.rest.RestResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChopBkendClientSrvc extends RestClient implements IChopBkendClientSrvc {

	public ChopBkendClientSrvc(RestTemplateBuilder restTemplateBuilder, 
								@Value("${rest.host}") String host, 
								@Value("${rest.host.port}") String port, 
								@Value("${rest.host.api}") String api, 
								@Value("${rest.auth.username}") String username, 
								@Value("${rest.auth.password}") String password,
								@Value("${rest.host.readtimeout}") Long readTimeout,
								@Value("${rest.host.conntimeout}") Long connTimeout) {
		
		super(restTemplateBuilder, host, port, api, username, password, readTimeout, connTimeout);
	}

	@Override
	public RestResponseDTO<List<ProductDTO>> getAllProducts() {
		
		log.info(Emj.INFO + " REST client: getAllProducts()");
		
		return makeTheCall( "/products", 
							  HttpMethod.GET, 
						      null,
						  	  new ParameterizedTypeReference<RestResponseDTO<List<ProductDTO>>>() {});	
	}

	@Override
	public RestResponseDTO<EventRegistryDTO> createEventRegistry(EventRegistryDTO er) {

		log.info(Emj.INFO + " REST client: createEventRegistry({})", er);
		
		HttpEntity<EventRegistryDTO> entity = new HttpEntity<EventRegistryDTO>(er);
		
		return makeTheCall( "/event", 
							  HttpMethod.POST, 
						      entity,
						  	  new ParameterizedTypeReference<RestResponseDTO<EventRegistryDTO>>() {});		
	} 
}

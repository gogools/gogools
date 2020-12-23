package com.gogools.chop.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.gogools.chop.dto.ProductDTO;
import com.gogools.enums.Emj;
import com.gogools.rest.RestClient;
import com.gogools.rest.RestResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BkendClientService extends RestClient implements IBkendClientService {
	
    
	public BkendClientService(RestTemplateBuilder restTemplateBuilder, 
								@Value("${rest.host}") String host, 
								@Value("${rest.host.port}") String port, 
								@Value("${rest.host.api}") String api, 
								@Value("${rest.auth.username}") String username, 
								@Value("${rest.auth.password}") String password,
								@Value("${rest.host.readtimeout}") Long readTimeout,
								@Value("${rest.host.conntimeout}") Long connTimeout) {
		
		super(restTemplateBuilder, host, port, api, username, password, readTimeout, connTimeout);
	}
	
	
	public RestResponseDTO<List<ProductDTO>> bulkProducts( Resource excel ) {
		
		log.info(Emj.INFO + " REST client: bulkProducts()");
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("excel", excel);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		return makeTheCall( "/bulkCatalog/", 
							   HttpMethod.POST, 
							   new HttpEntity<MultiValueMap<String, Object>>(body, headers),
						  	   new ParameterizedTypeReference<RestResponseDTO<List<ProductDTO>>>() {});
	}
	
	
	public RestResponseDTO<List<ProductDTO>> getAllProducts() {
		
		log.info(Emj.INFO + " REST client: getAllProducts()");
		
		return makeTheCall( "/products/",
						       HttpMethod.GET, 
					           null,
					  	       new ParameterizedTypeReference<RestResponseDTO<List<ProductDTO>>>() {});
	}
	
	
	/**
	 * https://stackoverflow.com/questions/16542892/handle-connection-and-read-timeouts-for-restclient-calls-in-android
	 * @param url
	 * @param method
	 * @param requestEntity
	 * @param respType
	 * @return the response, check com.gogools.web.RespEntityCreator
	 *
	private <T> ResponseDTO<T> makeTheCall(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, ParameterizedTypeReference<RestResponse<T>> respType) {
		
		try {
			
			ResponseEntity<RestResponse<T>> responseEntity = restTemplate.exchange( url, method, requestEntity, respType);
			
			log.info(Emj.INFO + " REST response > code {} - headers {} - body: {}", responseEntity.getStatusCode(), responseEntity.getHeaders(), responseEntity.getBody());
			
			ResponseDTO<T> dto = new ModelMapper().map(responseEntity.getBody(), new TypeToken<ResponseDTO<T>>() {}.getType());
			
			dto.setHeaders(responseEntity.getHeaders());
			dto.setStatus(responseEntity.getStatusCode());
			
			return dto;
			
		} catch (RestClientException e) {

			e.printStackTrace();
			log.error(Emj.ERR + " REST ERROR: {}", e.getMessage());
			
			return new ResponseDTO<T>("Communications error", null, new HashMap<String, List<String>>(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}*/
}


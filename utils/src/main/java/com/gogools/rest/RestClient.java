package com.gogools.rest;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.gogools.enums.Emj;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestClient {

	private final RestTemplate restTemplate;
    
    public final String apiUrl;
	
    /**
     * 
     * @Value("${rest.host}") String host, 
	 * @Value("${rest.host.port}") String port, 
	 * @Value("${rest.host.api}") String api, 
	 * @Value("${rest.auth.username}") String username, 
	 * @Value("${rest.auth.password}") String password,
	 * @Value("${rest.host.readtimeout}") Long readTimeout,
	 * @Value("${rest.host.conntimeout}") Long connTimeout
     */
	public RestClient(RestTemplateBuilder restTemplateBuilder, String host, String port, String api, String username, String password, Long readTimeout, Long connTimeout) {
		
		apiUrl = host + ":" + port + api;
		
        this.restTemplate = restTemplateBuilder
        						.basicAuthentication(username, password)
        						.setReadTimeout(Duration.ofSeconds(readTimeout))
        						.setConnectTimeout(Duration.ofSeconds(connTimeout))
        						.build();
    }
	
	
	/**
	 * https://stackoverflow.com/questions/16542892/handle-connection-and-read-timeouts-for-restclient-calls-in-android < check!!!
	 * @param url
	 * @param method
	 * @param requestEntity
	 * @param respType
	 * @return the response, check com.gogools.web.RespEntityCreator
	 */
	public <T> RestResponseDTO<T> makeTheCall(String operationName, HttpMethod method, @Nullable HttpEntity<?> requestEntity, ParameterizedTypeReference<RestResponseDTO<T>> respType) {
		
		log.info(Emj.INFO + " REST call > endpoint: {} - [{}]", apiUrl + operationName, method);
		
		try {
			
			ResponseEntity<RestResponseDTO<T>> responseEntity = restTemplate.exchange( apiUrl + operationName, method, requestEntity, respType);
			
			log.info(Emj.INFO + " REST response > code {} - headers {} - body: {}", responseEntity.getStatusCode(), responseEntity.getHeaders(), responseEntity.getBody());
			
			RestResponseDTO<T> dto = responseEntity.getBody();
			
			//auxiliar info, p.e. getAlertClass > HTML(porto) message
			dto.setHeaders(responseEntity.getHeaders());
			dto.setStatus(responseEntity.getStatusCode());
			
			log.info(Emj.OK + " REST RESPONSE: {}", dto);
			
			return dto;
			
		} catch (RestClientException e) {

			log.error(Emj.ERR + " REST ERROR: {}", e.getMessage());
			e.printStackTrace();
			
			return new RestResponseDTO<T>("Communications error", null, new HashMap<String, List<String>>(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
}


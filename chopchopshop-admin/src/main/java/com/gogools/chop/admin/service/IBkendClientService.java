package com.gogools.chop.admin.service;

import java.util.List;

import org.springframework.core.io.Resource;

import com.gogools.chop.dto.ProductDTO;
import com.gogools.rest.RestResponseDTO;

public interface IBkendClientService {

	public RestResponseDTO<List<ProductDTO>> bulkProducts( Resource excel );
	
	public RestResponseDTO<List<ProductDTO>> getAllProducts();
}

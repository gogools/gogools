package com.gogools.chop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gogools.chop.domain.Product;
import com.gogools.chop.dto.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper { 

	ProductMapper MAPPER = Mappers.getMapper( ProductMapper.class );
	
	ProductDTO 	product2ProductDTO( Product product );
	
	Product		 productDTO2Product( ProductDTO product );
}

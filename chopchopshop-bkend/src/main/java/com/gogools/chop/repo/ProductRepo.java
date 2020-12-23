package com.gogools.chop.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gogools.chop.domain.Product;

public interface ProductRepo extends MongoRepository<Product, String> {

	public Optional<Product> findByCode( String code );
	
	public Optional<Product> findByCodeAndActiveTrue( String code );

	public List<Product> findByBrandIdOrderByDescrip( Integer brand );
	
	public Page<Product> findByBrandIdOrderByDescrip( Integer brand, Pageable pageable );
	
	public Page<Product> findByBrandIdAndActiveTrueOrderByDescrip( Integer brand, Pageable pageable );
	
	public List<Product> findByQtyLessThanOrderByDescrip( Integer qty);
	
	public Page<Product> findAllOrderByDescrip(Pageable pageable);
	
	public List<Product> findAllOrderByDescrip();
}

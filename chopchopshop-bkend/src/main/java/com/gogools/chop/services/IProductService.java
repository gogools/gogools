package com.gogools.chop.services;

import java.util.List;

import com.gogools.chop.domain.Product;

public interface IProductService {
	
	public Product saveProduct( Product product);
	
	public Product updateProduct( Product product);
	
	public List<Product> saveProducts( List<Product> products);
	
	public Product getProductById( String idProduct );
	
	public Product getProductByCode( String code );
	
	public List<Product> getAllProducts();
	
	public Long countAll();
}

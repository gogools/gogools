package com.gogools.chop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogools.chop.domain.Product;
import com.gogools.chop.repo.ProductRepo;


@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepo repo;

	@Override
	public Product saveProduct(Product product) {

		return repo.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		
		if( product.getId() == null || product.getId().isEmpty() ) {
			
			return null;
		}
		
		Optional<Product> productBd = repo.findById( product.getId() );
		if( productBd.isPresent() ) {
			
			product.setId( productBd.get().getId() );
			product.setCode( productBd.get().getCode() );
		}
		else {
			
			return null;
		}
		
		return repo.save(product);		
	}

	@Override
	public List<Product> saveProducts(List<Product> products) {

		return repo.saveAll(products);
	}

	@Override
	public Product getProductById(String productId) {

		return repo.findById(productId).orElse(null);
	}

	@Override
	public Product getProductByCode(String code) {

		return repo.findByCode(code).orElse(null);
	}

	@Override
	public List<Product> getAllProducts() {

		return repo.findAll();
	}

	@Override
	public Long countAll() {
		
		return repo.count();
	}

}

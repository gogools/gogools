package com.gogools.sb.service.mongo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class GenService <T extends Domain,R extends MongoRepository<T,Object>> implements IGen<T> {

	@Autowired
	private R repo;

	@Override
	public T save(T t) {
		
		return repo.save(t);
	}

	@Override
	public T update(T t) {
		
		if( t.getId() == null || t.getId().isEmpty() ) {
			
			return null;
		}
		
		Optional<T> tBd = repo.findById( t.getId() );
		if( tBd.isPresent() ) {
			
			t.setId( tBd.get().getId() );
		}
		else {
			
			return null;
		}
		
		return repo.save(t);
	}

	@Override
	public List<T> saveMany(List<T> t) {

		return repo.saveAll(t);
	}

	@Override
	public T getById(String id) {
		
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<T> getAll() {
		
		return repo.findAll();
	}

}

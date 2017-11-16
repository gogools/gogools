package com.gogools.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gogools.demo.entities.Item;

public interface ReadingItemRepository extends JpaRepository<Item, Long>{

	List<Item> findByName( String name );
}

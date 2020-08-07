package com.gogools.sb.service.mongo;

import java.util.List;

public interface IGen<T> {
	
	public T save( T t );
	
	public T update( T t );
	
	public List<T> saveMany( List<T> t);
	
	public T getById( String id );
	
	public List<T> getAll();
}

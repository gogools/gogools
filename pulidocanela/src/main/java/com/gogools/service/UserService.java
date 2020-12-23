package com.gogools.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gogools.domain.User;
import com.gogools.enums.RoleEnum;
import com.gogools.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepo;
	
	public UserService( UserRepository userRepo ) {
		
		this.userRepo = userRepo;
	}
	

	public User getUserByName( String name ) {
		
		return userRepo.findByName( name );
	}
	
	
	public User getUserByEmail( String email ) {
		
		return userRepo.findByEmail( email );
	}
	
	
	public List<User> getAllUsers() {
		
		return userRepo.findAll();
	}
	
	
	public List<User> getAllUsersOrderByNameAsc() {
		
		return userRepo.findAllByOrderByNameAsc();
	}


	public List<User> updateInvites( List<User> users ) {
		
		userRepo.deleteAll();
		
		List<User> newUsers = new ArrayList<>();
		
		User marisa = new User("Marisa Canela", "canesa4@gmail.com", RoleEnum.SUPER_ROLE);
		User daniel = new User("Daniel Pulido", "gogools@gmail.com", RoleEnum.SUPER_ROLE);
		
		newUsers.add(marisa);
		newUsers.add(daniel);
		newUsers.addAll(users);
		
		return userRepo.saveAll( newUsers.stream().filter( distinctByKey(x -> x.getEmail())).collect(Collectors.toList()) );
	}
	
	public List<User> recueDatabase( ) {
		
		userRepo.deleteAll();
		
		List<User> newUsers = new ArrayList<>();
		
		User marisa = new User("Marisa Canela", "canesa4@gmail.com", RoleEnum.SUPER_ROLE);
		User daniel = new User("Daniel Pulido", "gogools@gmail.com", RoleEnum.SUPER_ROLE);
		
		newUsers.add(marisa);
		newUsers.add(daniel);
		
		return userRepo.saveAll( newUsers );
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
}

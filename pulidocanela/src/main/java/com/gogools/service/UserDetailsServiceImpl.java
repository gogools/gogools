package com.gogools.service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gogools.config.AuthUser;
import com.gogools.domain.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	UserService userService;

    @Override
    public UserDetails loadUserByUsername( String email ) throws UsernameNotFoundException {
    	
    	User user = userService.getUserByEmail( email );
        
        if (user == null) {
        	
        	UsernameNotFoundException exp = new UsernameNotFoundException("User not found whit email: " + email);
            logger.error(exp.getMessage());
            throw exp;
        }
        
        log.info("==LOGIN== USUARIO: {}-{}", user.getName(), user.getEmail());

        
        return new AuthUser(user.getEmail(), 
        		 			user.getPassword(), 
						    true, true, true, true, 
						    AuthorityUtils.createAuthorityList(user.getRole().getValue()), user.getName());
	}
}

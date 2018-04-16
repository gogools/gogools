package com.gogools.r3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogools.r3.domain.RestAccess;
import com.gogools.r3.repository.RestAccessRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
    @Autowired
    private RestAccessRepository accessRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	
        RestAccess access = accessRepository.findByUsername(userName);
        
        if (access == null) {
        	
        		UsernameNotFoundException exp = new UsernameNotFoundException("User not found: " + userName);
            logger.error(exp.getMessage());
            throw exp;
        }

        return new User(access.getUsername(), access.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList("CLIENT"));
    }
}

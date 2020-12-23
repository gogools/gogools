package com.gogools.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.gogools.enums.RoleEnum;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http
    		.csrf().disable()
        		.authorizeRequests()
        		.antMatchers("/css/**", "/js/**", "/login-static/**", "/invites/**", "/images/**", "/login", "/rescue").permitAll()
        		.antMatchers(HttpMethod.POST, "/data/**").permitAll()
        		.antMatchers("/index").hasAnyAuthority(RoleEnum.NORMAL_ROLE.getValue(), RoleEnum.SUPER_ROLE.getValue())
        		.antMatchers("/invitados").hasAnyAuthority(RoleEnum.SUPER_ROLE.getValue())
        		.anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .successHandler( customAuthenticationSuccessHandler() )
                .usernameParameter("username").passwordParameter("password")
                .failureUrl("/login?error=true")
                .failureHandler( customAuthenticationFailureHandler() )
                .permitAll()                
            .and()
            .logout()
                .permitAll()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");	
    }

    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
    
    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
    	
        return new CustomAuthenticationFailureHandler();
    }
    
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
    	
        return new CustomAuthenticationSuccessHandler();
    }
}

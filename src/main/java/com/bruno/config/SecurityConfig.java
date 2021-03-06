package com.bruno.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bruno.domain.service.CustomUserDetailsService;

import static com.bruno.config.SecurityConstantes.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUsuarioService;
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/filmes/**").hasRole("USER")
//			.antMatchers("/usuarios/**").hasRole("USER")
//			.antMatchers("/admin/**").hasRole("ADMIN")
//			.and()
//			.httpBasic()
//			.and()
//			.csrf().disable();
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.GET, SIGN_UP_URL).permitAll()
				.antMatchers("/*/admin/**").hasRole("ADMIN")
				.and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), customUsuarioService));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUsuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("usuariocomum").password("{noop}comum").roles("USER")
//		.and()
//		.withUser("usuarioadmin").password("{noop}admini").roles("USER", "ADMIN");
//	}
}

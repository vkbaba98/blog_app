package com.vishal.blog.blog_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vishal.blog.blog_app.filter.JwtFilter;
import com.vishal.blog.blog_app.service.UserInfoService;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	private JwtFilter jwtFilter;
	
	private final UserDetailsService userDetailsService;
	SecurityConfig(UserDetailsService userDetailsService){
		this.userDetailsService=userDetailsService;
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests((r)->r.requestMatchers("/post/**").authenticated())
		.authorizeHttpRequests((req)->req.requestMatchers("/login","/register").permitAll())
		
		.sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		.authenticationProvider(authenticationProvider())
		.build();
		
	}
@Bean
 public AuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
	daoAuthenticationProvider.setUserDetailsService(userDetailsService);
	daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
	return daoAuthenticationProvider;
}
@Bean
BCryptPasswordEncoder getPasswordEncoder() {
	return new BCryptPasswordEncoder();
}
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	return configuration.getAuthenticationManager();
}
}

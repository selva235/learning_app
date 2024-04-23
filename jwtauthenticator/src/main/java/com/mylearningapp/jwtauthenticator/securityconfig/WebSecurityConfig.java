package com.mylearningapp.jwtauthenticator.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	 @Autowired
	 private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	 @Autowired
	 private JwtRequestFilter jwtRequestFilter;
	 
	 @Bean
	 public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	     return http.getSharedObject(AuthenticationManagerBuilder.class)
	             .build();
	 }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.headers(headers -> headers.frameOptions().disable());
		
		httpSecurity.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((authorizeHttpRequests) ->
				authorizeHttpRequests
				.requestMatchers("/authenticate").permitAll()
				.requestMatchers("/h2-console/*").permitAll().anyRequest().authenticated())
			.exceptionHandling((exceptionHandler) -> 
				exceptionHandler.authenticationEntryPoint(jwtAuthenticationEntryPoint))
			.sessionManagement(sessionManage -> sessionManage.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        
        return httpSecurity.build();
    }
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
    }
}

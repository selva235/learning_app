package com.mylearningapp.jwtauthenticator.securityconfig;

import org.springframework.stereotype.Component;

@Component
public class JwtResponse {

	private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }
    
    public JwtResponse() {
		this.jwttoken = "";
    }

    public String getToken() {
        return this.jwttoken;
    }
}

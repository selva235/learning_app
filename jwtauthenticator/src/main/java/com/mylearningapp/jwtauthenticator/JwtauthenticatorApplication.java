package com.mylearningapp.jwtauthenticator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.mylearningapp.jwtauthenticator.entity.UserData;
import com.mylearningapp.jwtauthenticator.repository.UserDataRepo;

@SpringBootApplication
@EnableDiscoveryClient
public class JwtauthenticatorApplication {
	
	@Autowired
	private final UserDataRepo userDataRepo;
	
	public JwtauthenticatorApplication(final UserDataRepo userDataRepo) {

		this.userDataRepo = userDataRepo;
		List<UserData> userDataList = new ArrayList<>();
		UserData user1 = new UserData(null, "selva", "0000", "ROLE_USER,ROLE_ADMIN");
		UserData user2 = new UserData(null, "raj", "1111", "ROLE_ADMIN");
		UserData user3 = new UserData(null, "raji", "2222", "ROLE_USER");
		userDataList.add(user1);
		userDataList.add(user2);
		userDataList.add(user3);
		
		this.userDataRepo.saveAll(userDataList);
		
		this.userDataRepo.findAll().stream().forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(JwtauthenticatorApplication.class, args);
	}
	
	

}

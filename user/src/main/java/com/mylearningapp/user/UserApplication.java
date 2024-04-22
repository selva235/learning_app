package com.mylearningapp.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;

import com.mylearningapp.user.entity.User;
import com.mylearningapp.user.repository.UserRepository;


@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {
	
	private final UserRepository userRepo;
	@Autowired
	public UserApplication(final UserRepository repo) {
		this.userRepo = repo;
		
		List<User> userList = new ArrayList<>();
		userList.add(new User(Long.valueOf("1"), "selva", "selva@gmail.com", "9943449798", null, null));
		userList.add(new User(Long.valueOf("2"), "raji", "raji@gmail.com", "9943449798", null, null));
		userList.add(new User(Long.valueOf("3"), "john", "john@gmail.com", "9943449798", null, null));
		userList.add(new User(Long.valueOf("4"), "babu", "babu@gmail.com", "9943449798", null, null));
		userList.add(new User(Long.valueOf("5"), "prabha", "prabha@gmail.com", "9943449798", null, null));
		userList.add(new User(Long.valueOf("6"), "karan", "karan@gmail.com", "9943449798", null, null));
		
		this.userRepo.saveAll(userList);
	}

    @Bean
    @LoadBalanced
    WebClient.Builder loadBalancedWebClientBuilder() {
	   return WebClient.builder();
	}


	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}

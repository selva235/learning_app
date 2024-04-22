package com.mylearningapp.servicediscoveryandregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServicediscoveryandregistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicediscoveryandregistryApplication.class, args);
	}

}

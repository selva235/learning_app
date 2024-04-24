package com.mylearningapp.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.WebFilter;

import com.mylearningapp.gateway.filter.PostGlobalFilter;
import com.mylearningapp.gateway.filter.RequestFilter;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
	
	@Autowired
	private RequestFilter requestFilter;

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		
		return builder.routes()
				.route("user",r -> r.path("/userservice")
                        .and().method("POST")
                        .and().readBody(Object.class, s -> true).filters(f -> f.filters(requestFilter))
                        .uri("lb://user"))
				.route("user",r -> r.path("/userservice")
//                        .and().method("GET").filters(f-> f.filters(authFilter))
                        .uri("lb://user"))
				.build();
	}
	
	@Bean
    public WebFilter responseFilter(){
        return new PostGlobalFilter();
    }

}

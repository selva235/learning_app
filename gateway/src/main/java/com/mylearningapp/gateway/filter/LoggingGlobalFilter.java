package com.mylearningapp.gateway.filter;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingGlobalFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		final Logger logger = LoggerFactory.getLogger(LoggingGlobalFilter.class);
		ServerHttpRequest request = exchange.getRequest();
        Map<String, String> headers = request.getHeaders().toSingleValueMap();

        logger.info("REQUEST ID: " + request.getId());
        logger.info("METHOD: " + request.getMethod().name());
        logger.info("PATH: " + request.getPath());
        if(request.getBody() != null)
        	logger.info("BOD&Y: " + request.getBody().log());

        return chain.filter(exchange);
	}

}

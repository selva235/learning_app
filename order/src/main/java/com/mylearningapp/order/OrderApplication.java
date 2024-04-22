package com.mylearningapp.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.mylearningapp.order.entity.Order;
import com.mylearningapp.order.repository.OrderRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderApplication {

	private final OrderRepository orderRepo;
	@Autowired
	public OrderApplication(final OrderRepository repo) {
		this.orderRepo = repo;
		
		List<Order> orderList = new ArrayList<>();
		orderList.add(new Order(null, "OD_1001", 1L, 5L, "Pending", "Product at Bangalore Hub", null, null));
		orderList.add(new Order(null, "OD_1002", 2L, 2L, "Delivered", "Product Delivered", null, null));
		orderList.add(new Order(null, "OD_1003", 3L, 1L, "Returned", "Product Returned", null, null));
		orderList.add(new Order(null, "OD_1004", 4L, 3L, "Pending", "Product at Hariyana Hub", null, null));
		orderList.add(new Order(null, "OD_1005", 4L, 5L, "Pending", "Product at Chennai Hub", null, null));
		orderList.add(new Order(null, "OD_1006", 1L, 3L, "Delivered", "Product Delivered", null, null));
		
		this.orderRepo.saveAll(orderList);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}

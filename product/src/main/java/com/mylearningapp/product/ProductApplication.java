package com.mylearningapp.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.mylearningapp.product.entity.Product;
import com.mylearningapp.product.repository.ProductRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductApplication {

	private final ProductRepository productRepo;
	@Autowired
	public ProductApplication(final ProductRepository repo) {
		this.productRepo = repo;
		
		List<Product> productList = new ArrayList<>();
		productList.add(new Product(Long.valueOf("1"), "Green Tea", "10001", "Tata", BigDecimal.valueOf(254.80), null, null));
		productList.add(new Product(Long.valueOf("2"), "Gold Winner", "10002", "Adani", BigDecimal.valueOf(112.00), null, null));
		productList.add(new Product(Long.valueOf("3"), "Sprite", "10003", "Coca Cola", BigDecimal.valueOf(95.00), null, null));
		productList.add(new Product(Long.valueOf("4"), "Coconut oil", "10004", "VVD Gold", BigDecimal.valueOf(260.80), null, null));
		productList.add(new Product(Long.valueOf("5"), "Casual Shoes", "10005", "Adidas", BigDecimal.valueOf(1500.00), null, null));
		productList.add(new Product(Long.valueOf("6"), "Milk", "10006", "Aavin", BigDecimal.valueOf(23.50), null, null));
		
		this.productRepo.saveAll(productList);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}

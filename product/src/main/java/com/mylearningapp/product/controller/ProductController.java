package com.mylearningapp.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mylearningapp.product.entity.Product;
import com.mylearningapp.product.repository.ProductRepository;

@RestController
@CrossOrigin(origins = "http://localhost:9080")
public class ProductController {

	@Autowired
	private ProductRepository productRepo;
	
	@PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }
	
	@GetMapping("/products")
    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }
	
	@GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable Long id) {
        return productRepo.findById(id).get();
    }
	
	@PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
    	try{
    		productRepo.deleteById(id);
    		return "Product Deleted Successfully";
    	}catch(Exception ex) {
    		return "Product not found";
    	}
    }
}

package com.mylearningapp.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.mylearningapp.user.entity.Order;
import com.mylearningapp.user.entity.Product;
import com.mylearningapp.user.entity.User;
import com.mylearningapp.user.entity.UserOrderAggregator;
import com.mylearningapp.user.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@PostMapping("/addUser")
    public User addProduct(@RequestBody User user) {
        return userRepo.save(user);
    }
	
	@GetMapping("/users")
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }
	
	@GetMapping("/userById/{id}")
    public User findUserById(@PathVariable Long id) {
        return userRepo.findById(id).get();
    }
	
	@PutMapping("/update")
    public User updateUSer(@RequestBody User user) {
        return userRepo.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
    	try{
    		userRepo.deleteById(id);
    		return "User Deleted Successfully";
    	}catch(Exception ex) {
    		return "User not found";
    	}
    }
    
    @GetMapping("/ordersByUserId/{id}")
    public Flux<Order> getOrdersByUserId(@PathVariable Long id){
    	
    	Flux<Order> userOrders = webClientBuilder.build().get().uri("http://order/ordersByUserId/{id}", id).retrieve().bodyToFlux(Order.class);
    	return userOrders;
    }
    
//    @GetMapping("/orderAndProductDetailsByUserId/{id}")
//    public Mono<UserOrderAggregator> getOrderAndProductDetailsByUserId(@PathVariable Long id){
//    	
//    	return Mono.zip(
//    			userRepo.findById(id).get(),
////    			this.webClientBuilder.build().get().uri("http://order/ordersByUserId/{id}", id).retrieve().bodyToMono(Order.class)
////    			this.webClientBuilder.build().get().uri("http://product/productById/{id}", id).retrieve().bodyToMono(Product.class)
//    			).map(tuple -> {
//    				
//    			});
//    	
//    	Flux<Order> userOrders = webClientBuilder.build().get().uri("http://order/ordersByUserId/{id}", id).retrieve().bodyToFlux(Order.class);
//    	userOrders.doOnNext(order -> System.out.println(order.toString()));
//    	Flux<Product> products = webClientBuilder.build().get().uri("http://product/productById/{id}", id).retrieve().bodyToFlux(Product.class);
//    	products.doOnNext(product -> System.out.println(product.toString()));
//    	return userOrders;
//    }
    

}

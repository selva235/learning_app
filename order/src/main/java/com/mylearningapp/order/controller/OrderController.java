package com.mylearningapp.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mylearningapp.order.entity.Order;
import com.mylearningapp.order.repository.OrderRepository;

@RestController
public class OrderController {

	@Autowired
	private OrderRepository orderRepo;
	
	@PostMapping("/addOrder")
    public Order addOrder(@RequestBody Order order) {
        return orderRepo.save(order);
    }
	
	@GetMapping("/orders")
    public List<Order> findAllOrders() {
        return orderRepo.findAll();
    }
	
	@GetMapping("/ordersByUserId/{id}")
    public List<Order> findAllOrdersByUserId(@PathVariable Long id) {
        return orderRepo.findAllByUserId(id);
    }
	
	@GetMapping("/orderById/{id}")
    public Order findOrderById(@PathVariable Long id) {
        return orderRepo.findById(id).get();
    }
	
	@PutMapping("/update")
    public Order updateOrder(@RequestBody Order order) {
        return orderRepo.save(order);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
    	try{
    		orderRepo.deleteById(id);
    		return "Order Deleted Successfully";
    	}catch(Exception ex) {
    		return "Order not found";
    	}
    }
}

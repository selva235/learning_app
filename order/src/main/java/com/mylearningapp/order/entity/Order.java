package com.mylearningapp.order.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "order_details")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String orderId;
	private Long userId;
	private Long productId;
	private String status; //Delivered, Returned, Pending
	private String remarks;
	@CreationTimestamp
	private Date createdTimestamp;
	@UpdateTimestamp
	private Date updatedTimestamp;
}

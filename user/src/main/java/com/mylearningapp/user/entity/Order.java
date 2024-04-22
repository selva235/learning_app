package com.mylearningapp.user.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

	private Long id;
	private String orderId;
	private Long userId;
	private Long productId;
	private String status; //Delivered, Returned, Pending
	private String remarks;
	private Date createdTimestamp;
	private Date updatedTimestamp;
}

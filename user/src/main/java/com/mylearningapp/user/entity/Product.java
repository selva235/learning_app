package com.mylearningapp.user.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

	private Long id;
	private String name;
	private String productId;
	private String brand;
	private BigDecimal price;
	private Date createdTimestamp;
	private Date updatedTimestamp;
}

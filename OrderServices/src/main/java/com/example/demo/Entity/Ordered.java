package com.example.demo.Entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ordered {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
//	@Column(name="PRODUCT_ID")
	private long productId;
	
//	@Column(name="QUANTITY")
	private long quantity;
	
//	@Column(name="ORDERED_DATE")
	private Instant orderDate;
	
//	@Column(name="STATUS")
	private String orderStatus;
	
//	@Column(name="TOTAL_AMOUNT")
	private long amount;
	
}

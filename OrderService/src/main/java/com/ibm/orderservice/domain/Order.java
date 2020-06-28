package com.ibm.orderservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="orders")
public class Order {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name ="order_id")
	private Long orderId;
	
	@Column(name ="user_id")
	private Long userId;
	
	@Column(name ="product_id")
	private Long productId;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	
}

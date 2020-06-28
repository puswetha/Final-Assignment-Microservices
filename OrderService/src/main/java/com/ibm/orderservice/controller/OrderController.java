package com.ibm.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.orderservice.domain.Order;
import com.ibm.orderservice.dto.OrderDTO;
import com.ibm.orderservice.service.OrderServiceImpl;

@RequestMapping ("/orderservice")
@RestController
public class OrderController {
	
	@Autowired
	OrderServiceImpl aOrderServiceImpl;
	
	@PostMapping("/order/createorder")
	public ResponseEntity<String> createOrder(@RequestBody Order aOrder, @RequestHeader("Authorization") String authHeader) {
		OrderDTO createOrder = aOrderServiceImpl.createOrder(aOrder, authHeader);
		//return ResponseEntity.ok().body(createOrder);
		if(createOrder == null) {
			return new ResponseEntity<>(new String("Order Creation is failed,check application logs"), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new String("Order Creation is successful"), HttpStatus.CREATED);
		}
	}
	
	@GetMapping ("/orders/user/{userid}")
	public ResponseEntity<OrderDTO> findAllOrdersOfUser(@PathVariable (value ="userid") Long userId, @RequestHeader("Authorization") String authHeader) {
		OrderDTO allOrdersOfUser = aOrderServiceImpl.getAllOrdersOfUser(userId, authHeader);
		if(allOrdersOfUser != null) {
			return new ResponseEntity<>(allOrdersOfUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(allOrdersOfUser, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping ("/orders/user/{userid}/order/{orderid}")
	public ResponseEntity<OrderDTO> findOrderByUserIdAndOrderId(@PathVariable (value ="userid") Long userId, @PathVariable (value ="orderid") Long orderId, @RequestHeader("Authorization") String authHeader) {
		OrderDTO orderDetailsByUserIdAndOrderId = aOrderServiceImpl.getOrderDetailsByUserIdAndOrderId(userId, orderId, authHeader);
		if(orderDetailsByUserIdAndOrderId != null) {
			return new ResponseEntity<>(orderDetailsByUserIdAndOrderId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(orderDetailsByUserIdAndOrderId, HttpStatus.NOT_FOUND);
		}
	}
}

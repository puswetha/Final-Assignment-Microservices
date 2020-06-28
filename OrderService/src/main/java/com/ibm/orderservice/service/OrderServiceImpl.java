package com.ibm.orderservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ibm.orderservice.domain.Order;
import com.ibm.orderservice.dto.UserDTO;
import com.ibm.orderservice.dto.OrderDTO;
import com.ibm.orderservice.dto.OrderMapper;
import com.ibm.orderservice.dto.ProductDTO;
import com.ibm.orderservice.repository.OrderRepository;
import com.ibm.orderservice.restclient.ProductRestClient;
import com.ibm.orderservice.restclient.UserRestClient;

@Service
public class OrderServiceImpl {
	
	@Autowired(required = true)
	OrderRepository aOrderRepository;
	@Autowired(required = true)
	ProductRestClient aProductRestClient;
	@Autowired(required = true)
	UserRestClient aUserRestClient;
	@Autowired(required = true)
	OrderMapper mapper;
	
	 public OrderDTO createOrder(Order aOrder, String authHeader) {
		ResponseEntity<ProductDTO>  productDetails = aProductRestClient.searchByProductID(aOrder.getProductId());
		ResponseEntity<UserDTO>  userDetails = aUserRestClient.getUserDetails(aOrder.getUserId(), authHeader);
		if(productDetails.getBody().getId() != null && userDetails.getBody().getUsername() != null ) {
			Order order = aOrderRepository.save(aOrder);
			return mapper.convertOrderToOrderDTO(order);
		} else {
			return null;
		}
	 }
	 
	 public OrderDTO getOrderDetailsByUserIdAndOrderId(Long userId, Long orderId, String authHeader) {
		 ResponseEntity<UserDTO>  userDetails = aUserRestClient.getUserDetails(userId, authHeader);
		 OrderDTO aOrdersDTO = new OrderDTO();
		 if(userDetails.getBody().getUsername() != null) {
			 Optional<Order> order = aOrderRepository.findById(orderId);
			 if(order.isPresent()) {
				 if(order.get().getUserId() == userId) {
					 aOrdersDTO.setOrderId(order.get().getOrderId());
					 aOrdersDTO.setUserId(order.get().getUserId());
					 aOrdersDTO.setProductId(order.get().getProductId());
				 }
			 } else {
				 aOrdersDTO = null;
			 }
			 
		 } else {
			 aOrdersDTO = null;
		 }
		 return aOrdersDTO;
	 }
	 
	 public OrderDTO getAllOrdersOfUser(Long userId, String authHeader) {
		 ResponseEntity<UserDTO>  userDetails = aUserRestClient.getUserDetails(userId, authHeader);
		 if(userDetails.getBody().getUsername() != null) {
			List<Order> aList = aOrderRepository.findByUserId(userId);
			if(aList != null) {
				if(aList.size() > 1) {
					return mapper.convertOrderListToOrderDTOList(aList);
				} else if(aList.size() == 1) {
					return mapper.convertOrderToOrderDTO(aList.get(0));
				}
			}
		 }
		 return null;
	 }
	
}

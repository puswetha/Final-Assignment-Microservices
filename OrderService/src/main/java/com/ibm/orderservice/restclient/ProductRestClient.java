package com.ibm.orderservice.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.orderservice.dto.ProductDTO;

@FeignClient("product-service")
public interface ProductRestClient {
	@GetMapping("/product/{id}")
	public ResponseEntity<ProductDTO> searchByProductID(@PathVariable (value = "id") Long id);

}

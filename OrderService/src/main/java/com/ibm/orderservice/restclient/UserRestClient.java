package com.ibm.orderservice.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ibm.orderservice.dto.UserDTO;

@FeignClient("account-login-service")
public interface UserRestClient {
	
	@GetMapping("/loginservice/{id}")
	ResponseEntity<UserDTO> getUserDetails(@PathVariable (value= "id")Long id, @RequestHeader("Authorization") String authorization);

}

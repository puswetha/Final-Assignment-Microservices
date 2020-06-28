package com.ibm.loginservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.loginservice.dto.UserDTO;
import com.ibm.loginservice.serviceImpl.AccountLoginService;

@RequestMapping("/loginservice")
@RestController
public class AccountLoginController {
	
	@Autowired
	AccountLoginService accountLoginService;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserDetails(@PathVariable (value= "id")Long id){
		UserDTO aUserDTO = accountLoginService.getUserDetailsById(id);
		return ResponseEntity.ok().body(aUserDTO);
	}

}

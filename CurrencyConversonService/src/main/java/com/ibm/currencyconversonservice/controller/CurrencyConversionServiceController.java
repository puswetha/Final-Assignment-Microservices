package com.ibm.currencyconversonservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.currencyconversonservice.dto.CurrencyConversionDTO;
import com.ibm.currencyconversonservice.serviceimpl.CurrencyConversionService;

@RequestMapping("/currencyconversion")
@RestController
public class CurrencyConversionServiceController {
	
	@Autowired(required = true)
	CurrencyConversionService aConversionService;
	
	@GetMapping("/convert/")
	public ResponseEntity<CurrencyConversionDTO> getConversionAmount(@RequestParam (value ="countryCode") String countryCode, @RequestParam (value ="amount")double amount) {
		CurrencyConversionDTO curConvDTO = aConversionService.getConversionAmount(countryCode, amount);
		if(curConvDTO != null) {
			return new ResponseEntity<>(curConvDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(curConvDTO, HttpStatus.NOT_FOUND);
		}
	}
	
}

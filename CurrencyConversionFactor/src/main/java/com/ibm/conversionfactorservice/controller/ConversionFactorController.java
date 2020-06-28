package com.ibm.conversionfactorservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.conversionfactorservice.dto.ConversionFactorDTO;
import com.ibm.conversionfactorservice.service.ConversionFactorService;

@RequestMapping ("/CurrencyConversionFactor")
@RestController
public class ConversionFactorController {
	
	@Autowired(required = true)
	ConversionFactorService aConversionFactorService;
	
	@GetMapping("/country/{countryCode}")
	public ResponseEntity<ConversionFactorDTO> getConversionFactor(@PathVariable (value ="countryCode") String countryCode) throws Exception {
		ConversionFactorDTO conversionFactoryDTO = aConversionFactorService.getConversionFactoryByCountryCode(countryCode);
		if(conversionFactoryDTO != null) {
			return new ResponseEntity<>(conversionFactoryDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(conversionFactoryDTO, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addConversionFactor(@RequestBody ConversionFactorDTO aConvFactorDTO) throws Exception {
		//return new ResponseEntity<>(aConversionFactorService.addConversionFactor(aConvFactorDTO), HttpStatus.CREATED);
		ConversionFactorDTO addConversionFactorDTO = aConversionFactorService.addConversionFactor(aConvFactorDTO);
		if(addConversionFactorDTO == null) {
			return new ResponseEntity<>(new String("Adding ConversionFactor is failed, Check application logs"), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new String("ConversionFactor added successfully for country code:"+ addConversionFactorDTO.getCountryCode()+" "), HttpStatus.CREATED);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<ConversionFactorDTO> updateConversionFactor(@RequestParam (value = "countrycode") String countryCode, @RequestBody ConversionFactorDTO aConvFactorDTO) throws Exception{
		ConversionFactorDTO updateConversionFactorDTO = aConversionFactorService.updateConversionFactor(countryCode, aConvFactorDTO);
		if(updateConversionFactorDTO != null) {
			return new ResponseEntity<>(updateConversionFactorDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(updateConversionFactorDTO, HttpStatus.NOT_MODIFIED);
		}
	}
	
}

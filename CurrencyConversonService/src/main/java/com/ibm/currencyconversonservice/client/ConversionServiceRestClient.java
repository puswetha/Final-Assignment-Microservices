package com.ibm.currencyconversonservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.currencyconversonservice.dto.ConversionFactorDTO;

@FeignClient(name ="CurrencyConversionFactor")
public interface ConversionServiceRestClient {

	@GetMapping("/CurrencyConversionFactor/country/{countryCode}")
	public ConversionFactorDTO getConversionFactor(@PathVariable (value ="countryCode") String countryCode);
		
}

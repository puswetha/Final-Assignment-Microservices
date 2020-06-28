package com.ibm.currencyconversonservice.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.currencyconversonservice.client.ConversionServiceRestClient;
import com.ibm.currencyconversonservice.dto.ConversionFactorDTO;
import com.ibm.currencyconversonservice.dto.CurrencyConversionDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CurrencyConversionService {
	
	private static Logger logger = LoggerFactory.getLogger(CurrencyConversionService.class);
	
	@Autowired
	final ConversionServiceRestClient aRestClient;
	
	@Autowired(required = true)
	public CurrencyConversionService(ConversionServiceRestClient restClient) {
		this.aRestClient=restClient;
	}
	
	@HystrixCommand(fallbackMethod = "fallbackgetConversionAmount" )
	public CurrencyConversionDTO getConversionAmount(String countryCode, double amount) {
		ConversionFactorDTO conversionFactorDTO = aRestClient.getConversionFactor(countryCode);
		CurrencyConversionDTO aCurrencyConversionDTO = new CurrencyConversionDTO();
		if(conversionFactorDTO.getId() > 0) {
			double convertedAmount = conversionFactorDTO.getConversionFactor() * amount;
			aCurrencyConversionDTO.setId(conversionFactorDTO.getId());
			aCurrencyConversionDTO.setCountryCode(conversionFactorDTO.getCountryCode());
			aCurrencyConversionDTO.setConversionFactor(conversionFactorDTO.getConversionFactor());
			aCurrencyConversionDTO.setConversionAmount(convertedAmount);
			return aCurrencyConversionDTO;
		} 
		else {
			return null;
		}

	}
	
	public CurrencyConversionDTO fallbackgetConversionAmount(String countryCode, double amount) {
		logger.info("Circuit Breaker: Hystrix fallback method is executed");
		CurrencyConversionDTO aCurrencyConversionDTO = new CurrencyConversionDTO();
		aCurrencyConversionDTO.setCountryCode(countryCode);
		aCurrencyConversionDTO.setConversionFactor(30.30);
		aCurrencyConversionDTO.setId(100L);
		double convertedAmount = aCurrencyConversionDTO.getConversionFactor() * amount;
		aCurrencyConversionDTO.setConversionAmount(convertedAmount);
		aCurrencyConversionDTO.setFallbackExecuted(true);
		return aCurrencyConversionDTO;
	}
}

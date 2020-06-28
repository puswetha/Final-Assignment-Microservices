package com.ibm.conversionfactorservice.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ibm.conversionfactorservice.domain.ConversionFactor;
import com.ibm.conversionfactorservice.dto.ConversionFactorDTO;
import com.ibm.conversionfactorservice.dto.ConversionFactorMapper;
import com.ibm.conversionfactorservice.repository.ConversionFactorRepository;

@Service
public class ConversionFactorService {
	
	private static Logger logger = LoggerFactory.getLogger(ConversionFactorService.class);
	
	@Autowired
	ConversionFactorRepository aConversionFactorRepository;
	@Autowired
	ConversionFactorMapper aMapper;

	public ConversionFactorDTO getConversionFactoryByCountryCode(String countryCode) throws Exception {
		Optional<ConversionFactor> aConversionFactor = aConversionFactorRepository.getConversionFactoryByCountryCode(countryCode);
		if(aConversionFactor.isPresent()) {
			return aMapper.convertConverstionFactorToConversionFactorDTO(aConversionFactor.get());
		}
		else {
			logger.error("Requested resource not found"+ "country_code="+countryCode);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested resource not found "+ "country_code="+countryCode);
		}
	}
	
	public ConversionFactorDTO addConversionFactor(ConversionFactorDTO aConvFactorDTO) throws Exception{
		if(aConvFactorDTO != null) {
			ConversionFactor aConvFactor = aMapper.convertConverstionFactorDTOToConversionFactor(aConvFactorDTO);
			try {
				ConversionFactor convFactor = aConversionFactorRepository.save(aConvFactor);
				return aMapper.convertConverstionFactorToConversionFactorDTO(convFactor);
			}
			catch(Exception e) {
				logger.error("Unable to save data to DB", e);
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		}
		return null;
	}
	
	public ConversionFactorDTO updateConversionFactor(String countryCode, ConversionFactorDTO aConvFactorDTO) throws Exception {
		Optional<ConversionFactor> aConversionFactor = aConversionFactorRepository.getConversionFactoryByCountryCode(countryCode);
		if(aConversionFactor.isPresent()) {
			ConversionFactor aconvFactor = aConversionFactor.get();
			aconvFactor.setId(aConvFactorDTO.getId());
			aconvFactor.setCountryCode(aConvFactorDTO.getCountryCode());
			aconvFactor.setConversionFactor(aConvFactorDTO.getConversionFactor());
			try {
				ConversionFactor aCoFactor = aConversionFactorRepository.saveAndFlush(aconvFactor);
				return aMapper.convertConverstionFactorToConversionFactorDTO(aCoFactor);
			}
			catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, e.getMessage());
			}
		}
		else {
			logger.error("Requested resource not found"+ "country code="+countryCode);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested resource not found "+ "country_code="+countryCode);
		}
	}
	
}

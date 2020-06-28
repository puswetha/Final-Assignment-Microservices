package com.ibm.conversionfactorservice.dto;

import org.springframework.stereotype.Component;

import com.ibm.conversionfactorservice.domain.ConversionFactor;

@Component
public class ConversionFactorMapper {
	
	public ConversionFactorDTO convertConverstionFactorToConversionFactorDTO (ConversionFactor aConversionFactor) {
		ConversionFactorDTO aConversionFactorDTO = new ConversionFactorDTO();
		if (aConversionFactor != null) {
			aConversionFactorDTO.setId(aConversionFactor.getId());
			aConversionFactorDTO.setCountryCode(aConversionFactor.getCountryCode());
			aConversionFactorDTO.setConversionFactor(aConversionFactor.getConversionFactor());
		}
		return aConversionFactorDTO;
	}
	
	public ConversionFactor convertConverstionFactorDTOToConversionFactor (ConversionFactorDTO aConversionFactorDTO) {
		ConversionFactor aConversionFactor = new ConversionFactor();
		if (aConversionFactorDTO != null) {
			aConversionFactor.setId(aConversionFactorDTO.getId());
			aConversionFactor.setCountryCode(aConversionFactorDTO.getCountryCode());
			aConversionFactor.setConversionFactor(aConversionFactorDTO.getConversionFactor());
		}
		return aConversionFactor;
	}

}

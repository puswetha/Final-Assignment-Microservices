package com.ibm.conversionfactorservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.conversionfactorservice.domain.ConversionFactor;

@Repository
public interface ConversionFactorRepository extends JpaRepository<ConversionFactor, Long> {
	
	Optional<ConversionFactor> getConversionFactoryByCountryCode(String countryCode) ;

}

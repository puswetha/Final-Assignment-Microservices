package com.ibm.currencyconversonservice.dto;

public class CurrencyConversionDTO {
	
	private long id;
	private String countryCode;
	private double conversionFactor;
	private double conversionAmount;
	private boolean isFallbackExecuted;
	
	public CurrencyConversionDTO() {
		this.setFallbackExecuted(false);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public double getConversionFactor() {
		return conversionFactor;
	}
	public void setConversionFactor(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
	public double getConversionAmount() {
		return conversionAmount;
	}
	public void setConversionAmount(double conversionAmount) {
		this.conversionAmount = conversionAmount;
	}

	public boolean isFallbackExecuted() {
		return isFallbackExecuted;
	}

	public void setFallbackExecuted(boolean isFallbackExecuted) {
		this.isFallbackExecuted = isFallbackExecuted;
	}
	
}

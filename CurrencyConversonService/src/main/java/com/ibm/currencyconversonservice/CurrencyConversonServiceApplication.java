package com.ibm.currencyconversonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrixDashboard
@EnableHystrix
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class CurrencyConversonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversonServiceApplication.class, args);
	}

}

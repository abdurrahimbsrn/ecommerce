package com.ecommerce.urun_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ecommerce.urun_service")
public class UrunServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrunServiceApplication.class, args);
	}

}

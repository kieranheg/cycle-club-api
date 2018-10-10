package com.muscy.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {
	private static final Logger log = LoggerFactory.getLogger(ApiApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}

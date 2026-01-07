package com.ecom.kodewala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderPlaceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderPlaceServiceApplication.class, args);
	}

}

package com.order.wise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderWiseBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderWiseBackendApplication.class, args);
	}

}

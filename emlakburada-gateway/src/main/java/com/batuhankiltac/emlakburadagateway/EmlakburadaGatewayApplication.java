package com.batuhankiltac.emlakburadagateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmlakburadaGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmlakburadaGatewayApplication.class, args);
	}
}
package com.batuhankiltac.emlakburadaauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmlakburadaAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmlakburadaAuthApplication.class, args);
	}

}
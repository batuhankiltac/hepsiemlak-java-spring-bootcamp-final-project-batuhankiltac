package com.batuhankiltac.emlakburadaadvert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmlakburadaAdvertApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmlakburadaAdvertApplication.class, args);
	}

}
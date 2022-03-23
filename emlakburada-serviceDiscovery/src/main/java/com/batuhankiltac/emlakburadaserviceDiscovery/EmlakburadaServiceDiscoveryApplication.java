package com.batuhankiltac.emlakburadaserviceDiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EmlakburadaServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmlakburadaServiceDiscoveryApplication.class, args);
	}

}
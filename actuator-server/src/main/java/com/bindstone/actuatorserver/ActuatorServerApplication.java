package com.bindstone.actuatorserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class ActuatorServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActuatorServerApplication.class, args);
	}

}

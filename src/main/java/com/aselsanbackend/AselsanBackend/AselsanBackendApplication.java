package com.aselsanbackend.AselsanBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AselsanBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AselsanBackendApplication.class, args);
	}

}

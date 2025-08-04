package com.ditis.recp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class RecpApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecpApplication.class, args);
	}

}

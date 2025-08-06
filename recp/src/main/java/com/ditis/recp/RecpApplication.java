package com.ditis.recp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ServletComponentScan
public class RecpApplication extends SpringBootServletInitializer{
	
	/**
	 * Necessary configuration of the Application Builder  of the Application to make it possible to synchronize
	 * the local folder for the pattern's diagrams' images and the Server (TomCat)'s folder dedicated to
	 * Image files 
	 */
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RecpApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(RecpApplication.class, args);
	}

}

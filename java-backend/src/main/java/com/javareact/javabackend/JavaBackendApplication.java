package com.javareact.javabackend;

import com.javareact.javabackend.security.AppProperties;
import com.javareact.javabackend.shared.SpringApplicationContext;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JavaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaBackendApplication.class, args);
		System.out.println("Server is up and running");
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	@Bean(name = "applicationProperties")
	public AppProperties applicationProperties() {
		return new AppProperties();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}

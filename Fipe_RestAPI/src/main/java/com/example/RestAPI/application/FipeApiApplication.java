package com.example.RestAPI.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.example"})
@EnableMongoRepositories("com.example.RestAPI.repository")
public class FipeApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(FipeApiApplication.class, args);
	}
}
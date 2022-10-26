package com.example.simplerpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimpleRpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleRpaApplication.class, args);
	}

}

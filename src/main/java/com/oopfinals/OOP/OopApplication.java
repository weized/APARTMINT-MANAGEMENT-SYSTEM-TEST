package com.oopfinals.OOP;

import com.oopfinals.OOP.util.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class OopApplication {

	public static void main(String[] args) {
		// Load .env file
		EnvLoader.load(".env");
		SpringApplication.run(OopApplication.class, args);

	}

}

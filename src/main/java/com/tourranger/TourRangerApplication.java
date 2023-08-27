package com.tourranger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TourRangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourRangerApplication.class, args);
	}

}

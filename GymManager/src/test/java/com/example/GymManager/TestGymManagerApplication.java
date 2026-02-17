package com.example.GymManager;

import org.springframework.boot.SpringApplication;

public class TestGymManagerApplication {

	public static void main(String[] args) {
		SpringApplication.from(GymManagerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

package se_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import se_project.controller.Singleton;

@SpringBootApplication
public class SecureEmployeeApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecureEmployeeApplication.class, args);
		Singleton.getInstance();
	}
}

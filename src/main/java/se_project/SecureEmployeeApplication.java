package se_project;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import se_project.controller.Singleton;
import se_project.entity.StudentRegistration;
import se_project.service.StudentRegistrationService;
import se_project.service.StudentRegistrationServiceImpl;

@SpringBootApplication
public class SecureEmployeeApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecureEmployeeApplication.class, args);
		Singleton.getInstance();
	}
}

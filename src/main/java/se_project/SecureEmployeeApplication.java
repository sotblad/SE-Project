package se_project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import se_project.controller.Singleton;
import se_project.controller.statistics.Statistic;
import se_project.controller.statistics.StatisticFactory;

@SpringBootApplication
public class SecureEmployeeApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecureEmployeeApplication.class, args);
		Singleton.getInstance();
	}

}

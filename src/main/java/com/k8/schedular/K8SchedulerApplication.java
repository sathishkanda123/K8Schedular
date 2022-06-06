package com.k8.schedular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class K8SchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(K8SchedulerApplication.class, args);
	}
}
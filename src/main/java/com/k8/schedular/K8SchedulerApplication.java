package com.k8.schedular;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.util.ClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class K8SchedulerApplication {

	public static void main(String[] args) throws IOException, ApiException {
		SpringApplication.run(K8SchedulerApplication.class, args);
	}
}
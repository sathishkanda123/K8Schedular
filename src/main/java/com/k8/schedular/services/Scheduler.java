package com.k8.schedular.services;

import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.util.ClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class Scheduler {

    Logger logger = LoggerFactory.getLogger(Scheduler.class);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Scheduled(cron = "0 0 * * * SAT")
    public void deleteDeploymentResources() throws IOException {
        AppsV1Api api = new AppsV1Api(ClientBuilder.standard().build());
        logger.info("Delete a deployments started"+ sdf.format(LocalDateTime.now()));
    }

    @Scheduled(cron = "0 0 * * * MON")
    public void createOrUpdateDeploymentResources() throws IOException {
        AppsV1Api api = new AppsV1Api(ClientBuilder.standard().build());
        logger.info("Crate a deployments started"+ sdf.format(LocalDateTime.now()));
    }

}
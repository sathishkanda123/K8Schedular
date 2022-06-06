package com.k8.schedular.services;

import com.k8.schedular.config.MyKriProperties;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.V1DeleteOptions;
import io.kubernetes.client.util.ClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class Scheduler {

    @Autowired
    MyKriProperties myKriProperties;

    Logger logger = LoggerFactory.getLogger(Scheduler.class);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Scheduled(cron = "0 */1 * * * *")
    public void deleteDeploymentResources() throws IOException {
        AppsV1Api api = new AppsV1Api(ClientBuilder.standard().build());
        logger.info("Delete a deployments started"+ formatter.format(LocalDateTime.now()));
        myKriProperties.getDeployments().forEach(deployment -> {
            try {
                api.deleteNamespacedDeployment(deployment.getName(),deployment.getNamespace(),null,null,
                        0,false,null,null);
                logger.info("Successfully deployed"+ deployment.getName());
            } catch (ApiException e) {
                logger.error(e.getMessage());
            }
        });
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void createOrUpdateDeploymentResources() throws IOException {
        AppsV1Api api = new AppsV1Api(ClientBuilder.standard().build());
        logger.info("Crate a deployments started"+ formatter.format(LocalDateTime.now()));
    }

}
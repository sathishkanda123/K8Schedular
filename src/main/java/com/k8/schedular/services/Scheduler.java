package com.k8.schedular.services;

import com.k8.schedular.config.CreateDeploymentProperties;
import com.k8.schedular.config.MyKriProperties;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1DeploymentBuilder;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.kubernetes.client.util.Yaml.load;

@Service
public class Scheduler {

    @Autowired
    MyKriProperties myKriProperties;

    @Autowired
    CreateDeploymentProperties createDeploymentProperties;

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
                e.printStackTrace();
                logger.error(e.getResponseBody());
            }
        });
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void createOrUpdateDeploymentResources() throws IOException {
        AppsV1Api api = new AppsV1Api(ClientBuilder.standard().build());
        createDeploymentProperties.getCreateDeployments().forEach(deployment -> {
            try {
                Object fileDeployment = load(new File(deployment.getLocation()));
                V1Deployment v1Deployment = (V1Deployment) fileDeployment;
                api.createNamespacedDeployment(deployment.getNamespace(),v1Deployment ,null,null,null,null);
                logger.info("Sucessfully deployment created"+ deployment.getApplication()+"@"+formatter.format(LocalDateTime.now()));
            } catch (IOException | ApiException e) {
                e.printStackTrace();
                logger.info("Deployment creation failed.."+ deployment.getApplication()+"@"+ formatter.format(LocalDateTime.now())+"Exception is"+ e.getMessage());
            }
        });
    }

}
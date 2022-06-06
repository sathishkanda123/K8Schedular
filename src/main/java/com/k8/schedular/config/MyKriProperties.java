package com.k8.schedular.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "application")
@PropertySource(value = "classpath:deployments/deployments.yml", factory = YamlPropertySourceFactory.class)
public class MyKriProperties {

    private List<Deployment> deployments;

    public List<Deployment> getDeployments() {
        return deployments;
    }

    public void setDeployments(List<Deployment> deployments) {
        this.deployments = deployments;
    }

    public static class Deployment {

        private String name;
        private String svc;
        private String namespace;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSvc() {
            return svc;
        }

        public void setSvc(String svc) {
            this.svc = svc;
        }

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }
    }
}
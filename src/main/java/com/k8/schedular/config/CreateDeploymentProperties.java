package com.k8.schedular.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "application")
@PropertySource(value = "classpath:deployments/deployments.yml", factory = YamlPropertySourceFactory.class)
public class CreateDeploymentProperties {

    private List<CreateDeployment> createDeployments;

    public List<CreateDeployment> getCreateDeployments() {
        return createDeployments;
    }

    public void setCreateDeployments(List<CreateDeployment> createDeployments) {
        this.createDeployments = createDeployments;
    }

    public static class CreateDeployment {

        private String namespace;
        private String application;
        private String location;

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public String getApplication() {
            return application;
        }

        public void setApplication(String application) {
            this.application = application;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }



}
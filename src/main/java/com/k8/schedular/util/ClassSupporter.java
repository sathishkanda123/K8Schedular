package com.k8.schedular.util;

import io.kubernetes.client.common.KubernetesObject;
import io.kubernetes.client.openapi.models.*;

public class ClassSupporter {

    public static Class<? extends KubernetesObject> getClassForKind(String kind) {
        switch (kind) {
            case "pod":
            case "pods":
                return V1Pod.class;
            case "deployment":
            case "deployments":
                return V1Deployment.class;
            case "service":
            case "services":
                return V1Service.class;
            case "node":
            case "nodes":
                return V1Node.class;
            case "replicationcontroller":
            case "replicationcontrollers":
                return V1ReplicationController.class;
        }
        return null;
    }
}
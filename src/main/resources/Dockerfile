FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
COPY target/k8s-scheduler.jar k8s-scheduler.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/k8s-scheduler.jar"]
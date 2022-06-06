mvn clean install
docker build -t sathishkanda83/k8s-scheduler:v1 .
docker push sathishkanda83/k8s-scheduler:v1
kubectl create deployment k8-scheduler --image=sathishkanda83/k8s-scheduler:v1 -o=yaml > deployment.yaml
kubectl create service clusterip k8-scheduler --tcp=8080:8080 -o=yaml >> deployment.yaml
kubectl apply -f deployment.yaml

A Spring Scheduled tasks is like this:
1 2 3 4 5 6 Index
- - - - - -
* * * * * * command to be executed
- - - - - -
| | | | | |
| | | | | ------- Day of week (MON - SUN)
| | | | --------- Month (1 - 12)
| | | ----------- Day of month (1 - 31)
| |-------------- Hour (0 - 23)
| --------------- Minute (0 - 59)
----------------- Seconds (0 - 59)
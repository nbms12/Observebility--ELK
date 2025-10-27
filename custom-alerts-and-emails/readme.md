aim : to create cusotom metrics, (intrumentation) and create alert manager to recieve emails . 

source : https://github.com/iam-veeramalla/observability-zero-to-hero/tree/main/day-4
intrumentation : 
1. metrics 2. logs 3. traces

metrics types : 
1. counter( total numbers )  2. guage ( ups, downs of metrics )  3. histogram ( set of metrics in bkts ) 


steps:

```
eksctl create cluster --name=observability \
                      --region=us-east-1 \
                      --zones=us-east-1a,us-east-1b \
                      --without-nodegroup
```

```

eksctl utils associate-iam-oidc-provider \
    --region us-east-1 \
    --cluster observability \
    --approve
```

```
eksctl create nodegroup --cluster=observability \
                        --region=us-east-1 \
                        --name=observability-ng-private \
                        --node-type=t3.medium \
                        --nodes-min=2 \
                        --nodes-max=3 \
                        --node-volume-size=20 \
                        --managed \
                        --asg-access \
                        --external-dns-access \
                        --full-ecr-access \
                        --appmesh-access \
                        --alb-ingress-access \
                        --node-private-networking

```


Install kube-prometheus-stack

```
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update

```

Deploy the chart into a new namespace "monitoring"

```
kubectl create ns monitoring
cd day-2

helm install monitoring prometheus-community/kube-prometheus-stack \
-n monitoring \
-f ./custom_kube_prometheus_stack.yml
```
 Verify the Installation

 ```
kubectl get all -n monitoring
```

port forwardin 

```
kubectl port-forward service/monitoring-kube-prometheus-alertmanager -n monitoring 9093:9093 --address=0.0.0.0

kubectl port-forward service/monitoring-grafana -n monitoring 8080:80  --address=0.0.0.0
kubectl port-forward service/prometheus-operated -n monitoring 9090:9090 --address=0.0.0.0
```

 Kubernetes manifest ( day4 deploy app in dev namespace ) 

 ```
kubectl create ns dev

kubectl apply -k kubernetes-manifest/

```


Review the Alertmanager configuration files located in day-4/alerts-alertmanager-servicemonitor-manifest but below is the brief overview
Before configuring Alertmanager, we need credentials to send emails. For this project, we are using Gmail, but any SMTP provider like AWS SES can be used. so please grab the credentials for that.

Open your Google account settings and search App password & create a new password & put the password in day-4/alerts-alertmanager-servicemonitor-manifest/email-secret.yml
One last thing, please add your email id in the day-4/alerts-alertmanager-servicemonitor-manifest/alertmanagerconfig.yml

HighCpuUsage: Triggers a warning alert if the average CPU usage across instances exceeds 50% for more than 5 minutes.

PodRestart: Triggers a critical alert immediately if any pod restarts more than 2 times.

Apply the manifest files to your cluster by running:

```
kubectl apply -k alerts-alertmanager-servicemonitor-manifest/

```
once app password( convert base64 ) , email addresss is set, inside alerts-alertmanager-servicemonitor-manifest folder apply below code 

```
kubectl apply -k .
```

to test service-a crash alert use path as same 


normal app runs 
<img width="1366" height="502" alt="image" src="https://github.com/user-attachments/assets/4c49c9d9-431c-4e47-8393-de358ea897b0" />

http://ad7144c69605c40818f12f95a426796c-785506289.ap-south-1.elb.amazonaws.com/crash
email notification alert on crash of pod service-a 

<img width="994" height="592" alt="image" src="https://github.com/user-attachments/assets/67c3016c-89b5-4abc-9f25-599ac59cc0bc" />

# Clean UP

Uninstall helm chart:
```
helm uninstall monitoring --namespace monitoring

```
Delete namespace:
```
kubectl delete ns monitoring
kubectl delete ns dev
```

Delete Cluster & everything else:
```
eksctl delete cluster --name observability
```

<img width="1366" height="699" alt="image" src="https://github.com/user-attachments/assets/44fd4024-dacd-45d4-98a6-d37998fae517" />

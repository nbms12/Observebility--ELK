aim : to create cusotom metrics, (intrumentation) and create alert manager to recieve emails . 

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

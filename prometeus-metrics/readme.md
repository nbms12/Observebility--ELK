top metrics used : 
Kubernetes Cluster Metrics

# Memory usage
100 - (node_memory_MemAvailable_bytes / node_memory_MemTotal_bytes * 100)


# Pod & Container Metrics
# Pod CPU usage
sum(rate(container_cpu_usage_seconds_total[5m])) by (pod, namespace)

# Pod Memory usage
container_memory_usage_bytes
# Pod restarts
rate(kube_pod_container_status_restarts_total[5m])
# Container restarts
kube_pod_container_status_restarts_total


🚨 Application Performance Metrics

# HTTP Metrics
HTTP request rate
rate(http_requests_total[5m])


🔍 Kubernetes Specific Metrics

# Node status
kube_node_status_condition{condition="Ready",status="true"}

# Pod status
sum by (namespace) (kube_pod_status_phase{phase="Running"})

# Deployment availability
kube_deployment_status_replicas_available / kube_deployment_status_replicas

 # Resource Quotas
  Resource requests vs usage
kube_pod_container_resource_requests_cpu_cores
kube_pod_container_resource_limits_cpu_cores


# Uptime & Availability
 Service uptime
up{job="your-service"}
<img width="1362" height="656" alt="image" src="https://github.com/user-attachments/assets/c79d05d1-2d15-43e9-b569-ac9f103ab2eb" />




in grafana , selected dashboard as >> Kubernetes / Compute Resources / Namespace 

1.for busybox pod can see very less memory 

<img width="1354" height="689" alt="image" src="https://github.com/user-attachments/assets/4f127678-f456-48d9-a6fb-4af0936ddfe1" />

2.in monitorin namespace we can see number of pods are usin more memory 

<img width="1354" height="682" alt="image" src="https://github.com/user-attachments/assets/fe241526-f66b-4719-9025-ae31912d72c7" />


helm repo add bitnami https://charts.bitnami.com/bitnami
helm install kafka-service bitnami/kafka --set persistance.enabled=false
heml delete kafka-service
#!/bin/bash

#Documentation
#AWS-CLI configuration: https://www.youtube.com/watch?v=CYpy9aFIqxE&ab_channel=S3CloudHub
#AWS-EKSCTL: https://www.youtube.com/watch?v=p6xDCz00TxU&ab_channel=TechWorldwithNana
#AWS-Ingress: https://www.youtube.com/watch?v=CU8fXHK8Smk&ab_channel=FullStackBook
#AWS-Ingress: https://www.fullstackbook.com/devops/tutorials/how-to-set-up-aws-load-balancer-controller/


#to built eks cluster
eksctl create cluster --name upspoon-cluster --nodegroup-name linux-nodes --node-type t2.micro --nodes 2

#to delete eks cluster
eksctl delete cluster --name upspoon-cluster

#to get userId and arn
aws sts get-caller-identity

# run the script:
oidc_id=$(aws eks describe-cluster --name upspoon-cluster --query "cluster.identity.oidc.issuer" --output text | cut -d '/' -f 5)

aws iam list-open-id-connect-providers | grep $oidc_id

eksctl utils associate-iam-oidc-provider --cluster upspoon-cluster --approve


###
curl -o iam_policy.json https://raw.githubusercontent.com/kubernetes-sigs/aws-load-balancer-controller/v2.4.4/docs/install/iam_policy.json

aws iam create-policy \
    --policy-name AWSLoadBalancerControllerIAMPolicy \
    --policy-document file://iam_policy.json

eksctl create iamserviceaccount \
  --cluster=upspoon-cluster \
  --namespace=kube-system \
  --name=aws-load-balancer-controller \
  --role-name "AmazonEKSLoadBalancerControllerRole" \
  --attach-policy-arn=arn:aws:iam::980605516176:policy/AWSLoadBalancerControllerIAMPolicy \
  --approve

kubectl apply \
    --validate=false \
    -f https://github.com/jetstack/cert-manager/releases/download/v1.5.4/cert-manager.yaml

curl -Lo v2_4_4_full.yaml https://github.com/kubernetes-sigs/aws-load-balancer-controller/releases/download/v2.4.4/v2_4_4_full.yaml

sed -i.bak -e '480,488d' ./v2_4_4_full.yaml

sed -i.bak -e 's|your-cluster-name|upspoon-cluster|' ./v2_4_4_full.yaml

kubectl apply -f v2_4_4_full.yaml

curl -Lo v2_4_4_ingclass.yaml https://github.com/kubernetes-sigs/aws-load-balancer-controller/releases/download/v2.4.4/v2_4_4_ingclass.yaml

kubectl apply -f v2_4_4_ingclass.yaml

#following command verifies deployment runs
kubectl get deployment -n kube-system aws-load-balancer-controller

#following command returns ingress host
kubectl get ingress
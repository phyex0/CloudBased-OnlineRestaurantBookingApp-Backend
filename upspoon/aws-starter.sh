#!/bin/bash

#Documentation
#AWS-CLI configuration: https://www.youtube.com/watch?v=CYpy9aFIqxE&ab_channel=S3CloudHub
#AWS-EKSCTL: https://www.youtube.com/watch?v=p6xDCz00TxU&ab_channel=TechWorldwithNana
#AWS-Ingress: https://www.youtube.com/watch?v=CU8fXHK8Smk&ab_channel=FullStackBook
#AWS-Ingress: https://www.fullstackbook.com/devops/tutorials/how-to-set-up-aws-load-balancer-controller/


#To enable AWS CLI create a User and attach policy. After that create "Access Keys". When you have public and private keys:
#aws confiugre public_key, private_key


#to built eks cluster
eksctl create cluster --name upspoon-cluster --nodegroup-name linux-nodes --node-type t2.micro --nodes 5

#to delete eks cluster
eksctl delete cluster --name upspoon-cluster

#to get userId and arn
aws sts get-caller-identity


# run the script to add Ingress
oidc_id=$(aws eks describe-cluster --name upspoon-cluster --query "cluster.identity.oidc.issuer" --output text | cut -d '/' -f 5)

aws iam list-open-id-connect-providers | grep $oidc_id

eksctl utils associate-iam-oidc-provider --cluster upspoon-cluster --approve
###
curl -k -O https://raw.githubusercontent.com/kubernetes-sigs/aws-load-balancer-controller/v2.4.7/docs/install/iam_policy.json

aws iam create-policy \
    --policy-name AWSLoadBalancerControllerIAMPolicy \
    --policy-document file://iam_policy.json

eksctl create iamserviceaccount \
  --cluster=upspoon-cluster \
  --namespace=kube-system \
  --name=aws-load-balancer-controller \
  --role-name AmazonEKSLoadBalancerControllerRole \
  --attach-policy-arn=arn:aws:iam::980605516176:policy/AWSLoadBalancerControllerIAMPolicy \
  --approve

helm repo add eks https://aws.github.io/eks-charts

helm repo update

helm install aws-load-balancer-controller eks/aws-load-balancer-controller \
  -n kube-system \
  --set clusterName=upspoon-cluster \
  --set serviceAccount.create=false \
  --set serviceAccount.name=aws-load-balancer-controller

#following command verifies deployment runs
kubectl get deployment -n kube-system aws-load-balancer-controller

#following command returns ingress host
kubectl get ingress
#!/bin/bash

minikube start
minikube tunnel

kubectl port-forward --address 0.0.0.0 service/upspoon-gw 8080:8080
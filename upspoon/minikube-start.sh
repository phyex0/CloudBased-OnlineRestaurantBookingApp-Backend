#!/bin/bash

minikube start
minikube tunnel

kubectl port-forward --address 0.0.0.0 service/upspoon-gw 80:80
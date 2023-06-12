#!/bin/bash

#before do it don't forget to add mvn "C:\Users\Monster-PC\.m2\wrapper\dists\apache-maven-3.8.6-bin\1ks0nkde5v1pk9vtc31i9d0lcd\apache-maven-3.8.6\bin" as a path variable
# Clean and install Maven project
echo "Clean"
mvn clean -f ./pom.xml
sleep 5

echo "Install"
mvn install -f ./pom.xml
sleep 5

# Build Docker image with Jib
echo "Build Image"
mvn compile jib:dockerBuild -f ./pom.xml
sleep 5

#Tag Image
echo "Add Tag"
docker tag upspoon-payment:latest your-arn.dkr.ecr.us-east-1.amazonaws.com/upspoon-payment:latest
sleep 5

#Push Image to Amazon ECR
#To enable AWS CLI create a User and attach policy. After that create "Access Keys". When you have public and private keys:
#aws confiugre public_key, private_key
#Before push images don't forget to login:
#To get account Id use:
#aws sts get-caller-identity "Account" is the required for next step!
#aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin your-arn.dkr.ecr.us-east-1.amazonaws.com
echo "Push Image"
docker push your-arn.dkr.ecr.us-east-1.amazonaws.com/upspoon-payment:latest

echo "Press any key to continue"
while [ true ]; do
  read -t 3 -n 1
  if [ $? = 0 ]; then
    exit
  else
    echo "waiting for the keypress"
  fi
done

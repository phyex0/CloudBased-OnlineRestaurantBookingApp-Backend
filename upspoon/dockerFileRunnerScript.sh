#!/bin/bash
# @author: burak.yesildal
#Running all the docker compose files in a script.
#Open a git-bash and apply : sh .\dockerFileRunnerScript.sh

docker-compose -f ./organization/src/main/docker/kafka.yaml up -d
docker-compose -f ./organization/src/main/docker/postgre-organization.yaml up -d
docker-compose -f ./order/src/main/docker/postgre-order.yaml up -d
docker-compose -f ./stock/src/main/docker/postgre-stock.yaml up -d
docker-compose -f ./payment/src/main/docker/postgre-payment.yaml up -d
docker-compose -f ./booking/src/main/docker/postgre-booking.yaml up -d

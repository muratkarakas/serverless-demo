#!/bin/bash

./mvnw clean package -Pnative -Dnative-image.docker-build=true
docker build -t mkarakas/cloud-event-generator-java .

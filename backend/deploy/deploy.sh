#!/bin/bash

docker pull rubeha/shub_class

docker-compose down
docker-compose up -d

docker exec shub_class_api_1 npm run db:migrate
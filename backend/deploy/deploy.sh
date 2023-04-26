#!/bin/bash

docker pull rubeha/shub_class

docker-compose down
docker-compose up -d

docker exec backend-api-1 npm run db:create
docker exec backend-api-1 npm run db:migrate
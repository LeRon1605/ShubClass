#!/bin/bash

cd /usr/app/src/database
npx sequelize-cli db:create
npx sequelize-cli db:migrate

cd /usr/app/src
node app.js
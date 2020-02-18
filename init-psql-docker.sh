# Run a container with name "postgres-spring", set the enviroment variable "POSTGRES_PASSWORD" to "martin12", internal and external port set to 5432, and the name of the image "postgres:alpine"
docker run --name postgres-spring -e POSTGRES_PASSWORD=martin12 -d -p 5432:5432 postgres:alpine
# View running containers
docker ps
# Check the port of the "postgres-spring" container
docker port postgres-spring
# Access via ssh to the container
docker exec -it CONTAINER_ID bin/bash

# Once there, access to postgreSQL
psql -U postgres

# Once there, create a new database named "dofus"
CREATE DATABASE dofus;
# Connect to the new database
\c dofus
# Create the extension to generate UUIDs
CREATE EXTENSION "uuid-ossp";
# Generate a sample UUID
SELECT uuid_generate_v4();

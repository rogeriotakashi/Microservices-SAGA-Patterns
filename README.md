# Description
Practical implementation of microservice SAGA pattern, using a generic product order business case.
This is my first project using microservice architecture and all the code will improved as times go by. 


# Project Diagram
![Alt Text](https://github.com/rogeriotakashi/Microservices-SAGA-Patterns/blob/88dbb6ba9fb37a29d688b63636c460d6096074d6/Microservice-SAGA-Patterns-diagram.jpg)

# Tecnologies
- Java 8 (Streams, Optional, Stream API, ...)
- Springboot 
- Spring Cloud (Eureka, Config, Sleuth, ...)
- Swagger2 (All documentation centralized in DocumentationService)
- Kafka
- H2Db (Temporarilly using in-memory DB)
- Lombok 
- Maven

# To be implemented
- Compensation Functions
- Spring Zuul
- JWT and OAuth2
- Spring Feign
- Spring Security
- Logging
- Docker images and Docker-Composer


# Running Locally
After clonning the repository, you will need:
- Add Lombok Plugin for your IDE (https://projectlombok.org/)
- Import project using Maven
- Install and Run Zookeper and Kafka locally (All ports are set to default)

# Patterns and Project Changes
Until i started writing this description, the project pattern was changed 2 times.
Before each change, the pattern was backed up in a separeted branch for future reference (to myself)

- 03/03/2020 -> Created branch "choreography-pattern" (Changed from choregraphed pattern to orchestrated pattern)
- 06/03/2020 -> Created branch "Orcherstrator-Without-Kafka" (Changed from Orchestrated/Sync/Sequencial [Using just Rest calls] to Orchestrated/Async/Sequencial [Using Kafka])

- Since 07/03/2020 -> The current code (Branch master) is trying to implement a Orchestrated, Asynchronous and Sequencial scenario using Kafka Producers and Consumers (On going).
 

## Orchestrated, Asynchronous, and Sequential (Branch master)
![Alt Text](https://dzone.com/storage/temp/9338715-ezgifcom-optimize.gif)


## Orchestrated, Synchronous, and Parallel (Branch Orcherstrator-Without-Kafka)
![Alt Text](https://dzone.com/storage/temp/9338801-ezgifcom-optimize-3.gif)

## De-Centralized and Synchronous (Branch choreography-pattern)
![Alt Text](https://dzone.com/storage/temp/9338783-ezgifcom-optimize-1.gif)

# Change log
- 07/03/2020 -> Adding Kafka Consumer and Producer for some services (On going).
- 06/03/2020 -> Created branch "Orcherstrator-Without-Kafka" (changed from Orchestrated/Sync/Sequencial [Using just Rest calls] to 
- 03/03/2020 -> Created branch "choreography-pattern" (Changed from choregraphed pattern to orchestrated pattern)


# Sources and Reference (On going)
- All gifs source: https://dzone.com/articles/patterns-for-microservices-sync-vs-async


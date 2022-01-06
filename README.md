# stockchecker

A Spring Boot API that allows for checking & updating Products and their Stock counts in MongoDB.

API guidelines are loosely based on [Zalandos RESTful API Guidelines](https://opensource.zalando.com/restful-api-guidelines/#)


## Requirements 
- Java 1.8+
- Maven 3.6
- [MongoDB](https://docs.mongodb.com/manual/administration/install-community/)


## Architecture

- User -> HTTP API -> Service Level (e.g. AWS EC2) <--> MongoDB
  - GET/PUT/DELETE product 
  - GET stock

- Arch diagram ++ future upstream/downstream services
    - API for stock check **internal to product - deployed in a VPC
    - Additional service for placing order -- hits stock check API -- service discovery
    - Publish update events to support analytics ++ additional DB for analytics

- Swagger

- Endpoints for getting information about stock
    - /products               (GET all products)
    - /product/{id}           (GET specific product)
    - /product/{id}           (PUT specific product)
    - /product/{id}           (DELETE specific product)
  
    - /stock                  (GET all stock)
    - /stock/{id}             (GET specific stock)
    - /stock/{id}             (PATCH specific stock) Partial fail or fully in the example 2 items requested but 1 available?
    'Stock automatically created/deleted when product is created/deleted'
      'PUT/POST/DELETE not supported'
    

## TODO 

- API Pagination
- Coverage and checkstyle  
- CICD: automated builds on commit
- Strongly consistent reads to ensure most up to date information on read
- Add spring actuator and Promtheus for metrics/micrometer

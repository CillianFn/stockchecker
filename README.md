# stockchecker

## Requirements 
- Java 1.8+
- Maven 3.6
- MongoDB
    - https://docs.mongodb.com/manual/administration/install-community/

## TODO 

- Arch diagram ++ future upstream/downstream services
    - API for stock check **internal to product - deployed in a VPC
    - Additional service for placing order -- hits stock check API -- service discovery
    - Publish update events to support analytics ++ additional DB for analytics

- MongoDB
- Swagger

- Endpoints for getting information about stock
    - /products               (GET all products - paginated)
    - /product/{id}           (GET specific product)
    - /product/{id}           (PUT specific product)
    - /product/{id}           (DELETE specific product)
  
    - /stock                  (GET all stock - paginated
    - /stock/{id}             (GET specific stock)
    - /stock/{id}             (PATCH specific stock) Partial fail or fully in the example 2 items requested but 1 available?
    'Stock automatically created/deleted when product is created/deleted'
      'PUT/POST/DELETE not supported'
    

- Strongly consistent reads to ensure most up to date information

- Unit and integration testing. JUnit & Mockito
- Embedded Mongo?
- Add spring actuator and Promtheus for metrics/micrometer

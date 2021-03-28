# stockchecker

## TODO 

- Arch diagram ++ future upstream/downstream services
    - API for stock check **internal to product - deployed in a VPC - no auth required
    - Additional service for placing order -- hits stock check API -- service discovery
    - Publish update events to support analytics ++ additional DB for analytics

- MongoDB
- Swagger

- Endpoints for getting information about stock
    - /stock                  (GET all stock - paginated)
    - /stock/{id}             (GET stock of specific item)
    - /stock/{id}/metadata    (GET additional metadata about stock of specific item)

- Endpoints for updating stock
    - /stock/{id}             PUT update stock - takes quantity as an input? Partial fail or fully in the example 2 items requested but 1 available

- Strongly consistent reads to ensure most up to data information

- Models / contracts
- Unit and integration testing. JUnit & Mockito
- Embedded Mongo?
- Deploy on cloud infrastructure
- Cloudformation - API Gateway - EC2 - Hosted mongodb
- CICD
- Add spring actuator and Promtheus for metrics/micrometer

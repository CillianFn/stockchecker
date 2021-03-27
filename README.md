# stockchecker

## TODO 

Spring Boot
MongoDB local

API for stock check **internal to product - deployed in a VPC - no auth required

Endpoints for getting information about stock
products/stock                  GET all stock - paginated
product/stock/cache            GET all stock cached
product/stock/{id}             GET stock of specific item
product/stock/{id}/cache       GET specific stock cached
** Should all GETs be cached? Look up better naming convention for endpoint names

product/stock/{id}/metadata    GET additional metadata about stock of specific item

Endpoints for updating stock
/stock/{id}             PUT update stock - takes quantity as an input? Partial fail or fully in the example 2 items requested but 1 available
** Service level should update cache -- cache invalidation -- cache warming

Strongly consistent reads to ensure most up to data information

Additional service for placing order -- hits stock check API -- service discovery

Include above info in the readme

TODO
Create README
Create TODO
Import dependencies in pom
Setup project structure
Implement controller/service/storage layer
Implement models
Unit and integration testing
Embedded Mongo?
Swagger docs
Publish update events to support analytics ++ additional DB for analytics
Deploy on cloud infrastructure
Cloudformation - API Gateway - EC2 - Hosted mongodb

CICD


# stockchecker

A Spring Boot API that allows for checking & updating Products and their Stock counts in MongoDB.

API guidelines are loosely based on [Zalandos RESTful API Guidelines](https://opensource.zalando.com/restful-api-guidelines/#)


## Requirements 
- Java 1.8+
- Maven 3.6
- [MongoDB](https://docs.mongodb.com/manual/administration/install-community/)


## API 

- Products can be created, updated, deleted and retrieved via the `/product/{id}` endpoint.
- When a Product object is created, a Stock object is automatically created.
- There is a 1-1 mapping between Product and Stock objects. 
- It is not possible to create or delete Stock objects via the API.
- IDs must be passed when creating/updating any item to ensure idempotency.
- It is possible to update a Stock object via the PATCH endpoint.

## Swagger
 - The Swagger docs can be found at `http://localhost:8080/swagger-ui/index.html#/



## TODO 

- API Pagination
- Coverage and checkstyle
- CICD: automated builds on commit
- Strongly consistent reads to ensure most up to date information on read
- JSON files for test data
- Additional microservice that uses product/stock APIs

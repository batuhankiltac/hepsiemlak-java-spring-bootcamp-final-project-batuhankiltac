# HEPSİEMLAK JAVA/SPRING BOOTCAMP - FINAL PROJECT

---
## SUMMARY
This project includes restful services of the web application named Emlakburada. It is written using the Spring framework. With this application, which was created using the Microservice architecture, the following can be done:

```
ADVERT  -> GET / POST / PUT / DELETE
USER    -> GET / POST / PUT / DELETE
PRODUCT -> GET / POST /  -  /   -
BANNER  -> GET / POST / PUT / DELETE
PAYMENT ->  -  / POST /  -  /   -
```

The project also includes AuthN/AuthZ service , API Gateway and Service Discovery.

The project was created with as much attention as possible to the SOLID principles and the Clean Code approach.

Exception and Log mechanisms are also used in the project.

---
## TECHNOLOGICAL DETAILS
* Java 11
* JUnit
* Spring
* Maven
* Restfull
* MySQL
* Hibernate
* RabbitMQ
* Feign Client
* Netflix-Eureka
* Microservice Architecture
* SOLID Principles

---
## API USAGE
- #### ADVERT

```http
GET /adverts
```
```http
GET /adverts/{id}
```
```http
GET /adverts/users/{id}
```
```http
GET /adverts/users/actives/{userId}
```
```http
GET /adverts/users/passives/{userId}
```
```http
POST /adverts
```
```http
PUT /adverts
```
```http
PUT /adverts/status/{id}
```
```http
DELETE /adverts/{id}
```
- #### USER

```http
GET /users
```
```http
GET /users/{id}
```
```http
GET /users/products/{userId}
```
```http
GET /users/getIdIfQuantityExist/{userId}
```
```http
POST /users
```
```http
PUT /users
```
```http
PUT /users/{userId}/products/{productId}
```
```http
DELETE /users/{id}
```
- #### PRODUCT

```http
GET /products/{id}
```
```http
POST /products
```
- #### BANNER

```http
GET /banners
```
```http
GET /banners/{id}
```
```http
POST /banners
```
```http
PUT /banners/{id}
```
```http
DELETE /banners/{id}
```
- #### PAYMENT

```http
POST /payments
```

---
## UML DIAGRAM
![UML](https://user-images.githubusercontent.com/81926452/159790755-f2261259-f349-4083-ad34-8b7def2c5acd.png)

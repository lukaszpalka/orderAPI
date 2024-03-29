# orderAPI
Java 17 / Spring Boot 3.2.2 / Maven application for order management, created following a three-tier architecture - repository, service and controller (controllers are sending HTTP requests using REST). The packaging format is JAR. The application uses an H2 database with a pre-created data.sql file containing several queries to add records to the database.

---

# How to Run
To run the application, follow these steps:
* Clone this repository.
* Make sure you are using JDK 8 and Maven 3.8.5 or higher
* Set your credentials for H2 database by editing the application.properties file.

### Build the project
In your terminal navigate to the root directory of the project and run following Maven command:
```
mvn clean install
```

### Run the application
```
mvn spring-boot:run
```

Alternatively, you can run the app in your IDE. 

---

# Service
The application is just a simple REST service. You can perform some CRUD operations:
* Create new order
* Get all orders
* Get the latest order
* Remove order
* Update item in order

#### To do any of these, follow the steps below:

### Create new order
````
Endpoint: POST /order
Request Body: JSON with a list of ItemDto objects (String name, Double price, Integer amount)
````
````
Postman:
POST /order

Body:
[
{
"name": "Item 1",
"price": 10.5,
"amount": 2
},
{
"name": "Item 2",
"price": 15.75,
"amount": 3
}
]
````
````
curl:
curl -X POST http://localhost:8080/order -H "Content-Type: application/json" -d '[{"name": "Item 1", "price": 10.5, "amount": 2},{"name": "Item 2", "price": 15.75, "amount": 3}]'
````
````
HTTP Response Code: 201 (Created)
````

### Get all orders


```
Endpoint: GET /order
```

```
Postman:
GET /order

curl:
curl http://localhost:8080/order
```
```
HTTP Response Code: 200 (OK)
```

### Get the latest order

```
Endpoint: GET /order/last
```
```
Postman:
GET /order/last

curl:
curl http://localhost:8080/order/last
```
```
HTTP Response Code: 200 (OK) if found, 404 (Not Found) if not found
```

### Remove order
```
Endpoint: DELETE /order/{id}
```
```
Postman:
DELETE /order/123

curl:
curl -X DELETE http://localhost:8080/order/123
```
```
HTTP Response Code: 204 (No Content)
```

### Update item in order
```
Endpoint: PATCH /item/{id}
Query Parameters: name, price, amount (all optional)
```
```
Postman:
PATCH /item/456?name=UpdatedItem&price=20&amount=5

curl:
curl -X PATCH http://localhost:8080/item/456?name=UpdatedItem&price=20&amount=5
```
```
HTTP Response Code: 200 (OK) if updated, 404 (Not Found) if item not found
```
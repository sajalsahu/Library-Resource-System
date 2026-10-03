# 📚 Library Resource System

A **Library Resource System** is a RESTful backend application developed using **Java and Spring Boot** to manage library books efficiently.

The application provides APIs to **add, retrieve, update, partially update, delete, and search books** based on different criteria such as author, title, price range, availability, published year, and genre.

---

## 🚀 Features

* 📖 Add a single book
* 📚 Add multiple books at once
* 🔍 Get all books
* 🔎 Get a book by ID
* ✏️ Update complete book details
* 🛠️ Partially update book details using PATCH
* 🗑️ Delete a book
* ✍️ Find books by author
* 🔍 Find a book by title and author
* 💰 Find books within a price range
* ✅ Find books based on availability
* 📅 Find books by published year
* 🏷️ Find books by genre
* 📦 Standardized API response using `ResponseStructure`
* 🏗️ Layered architecture using Controller, Service, Repository, Entity, and DTO

---

## 🛠️ Technologies Used

| Technology      | Purpose                 |
| --------------- | ----------------------- |
| Java            | Programming Language    |
| Spring Boot     | Backend Framework       |
| Spring Web      | REST API Development    |
| Spring Data JPA | Database Operations     |
| Hibernate       | ORM                     |
| MySQL           | Database                |
| Maven           | Dependency Management   |
| Postman         | API Testing             |
| IntelliJ IDEA   | Development Environment |

---

## 🏗️ Project Architecture

The project follows a layered architecture:

```text
Client / Postman
       ↓
Controller Layer
       ↓
Service Layer
       ↓
Repository Layer
       ↓
Database
```

### Layers

**Controller**

* Handles HTTP requests.
* Defines REST API endpoints.
* Returns `ResponseEntity`.

**Service**

* Contains business logic.
* Communicates with the repository layer.

**Repository**

* Handles database operations using Spring Data JPA.

**Entity**

* Represents the `Book` table in the database.

**DTO**

* Contains the common API response structure.

---

# 📁 Project Structure

```text
LibraryResourceSystem
│
├── src
│   └── main
│       ├── java
│       │   └── LibraryResourceSystem
│       │       │
│       │       ├── controller
│       │       │   └── BookController.java
│       │       │
│       │       ├── Entity
│       │       │   └── Book.java
│       │       │
│       │       ├── repository
│       │       │   └── BookRepository.java
│       │       │
│       │       ├── service
│       │       │   └── bookService.java
│       │       │
│       │       └── dto
│       │           └── ResponseStructure.java
│       │
│       └── resources
│           └── application.properties
│
└── pom.xml
```

---

# 📖 Book Entity

The `Book` entity represents the book information stored in the database.

```java
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String author;
    private String genre;
    private Double price;
    private Integer publisedYear;
    private Boolean abailability;
}
```

## Entity Fields

| Field          | Data Type | Description                             |
| -------------- | --------- | --------------------------------------- |
| `id`           | Integer   | Unique identifier of the book           |
| `title`        | String    | Title of the book                       |
| `author`       | String    | Author of the book                      |
| `genre`        | String    | Genre/category of the book              |
| `price`        | Double    | Price of the book                       |
| `publisedYear` | Integer   | Year in which the book was published    |
| `abailability` | Boolean   | Indicates whether the book is available |

> **Note:** The current entity uses the field names `publisedYear` and `abailability`. For cleaner naming, these can later be changed to `publishedYear` and `availability`.

---

# 🔗 Base URL

```text
http://localhost:8080/api/book
```

---


# 📊 API Summary

| Method   | Endpoint                            | Description              |
| -------- | ----------------------------------- | ------------------------ |
| `POST`   | `/api/book`                         | Add a book               |
| `POST`   | `/api/book/all`                     | Add multiple books       |
| `GET`    | `/api/book`                         | Get all books            |
| `GET`    | `/api/book/{id}`                    | Get book by ID           |
| `PUT`    | `/api/book`                         | Update complete book     |
| `PATCH`  | `/api/book/{id}`                    | Partially update book    |
| `DELETE` | `/api/book/{id}`                    | Delete book              |
| `GET`    | `/api/book/author/{author}`         | Find by author           |
| `GET`    | `/api/book/{title}/{author}`        | Find by title and author |
| `GET`    | `/api/book/{startprice}/{endprice}` | Find by price range      |
| `GET`    | `/api/book/availibility`            | Find available books     |
| `GET`    | `/api/book/year/{year}`             | Find by published year   |
| `GET`    | `/api/book/genre/{genre}`           | Find by genre            |

---

# 📦 Response Structure

The APIs use a common response wrapper:

```java
ResponseStructure<T>
```

This provides a consistent format for API responses.

A typical response can look like:

```json
{
    "statusCode": 200,
    "message": "Book fetched successfully",
    "data": {
        "id": 1,
        "title": "Clean Code",
        "author": "Robert C. Martin",
        "genre": "Programming",
        "price": 550.0,
        "publisedYear": 2008,
        "abailability": true
    }
}
```

For multiple books:

```json
{
    "statusCode": 200,
    "message": "Books fetched successfully",
    "data": [
        {
            "id": 1,
            "title": "Clean Code",
            "author": "Robert C. Martin",
            "genre": "Programming",
            "price": 550.0,
            "publisedYear": 2008,
            "abailability": true
        }
    ]
}
```

---

# ⚙️ HTTP Status Codes Used

| Status Code                 | Meaning                        |
| --------------------------- | ------------------------------ |
| `200 OK`                    | Request completed successfully |
| `201 CREATED`               | New book/resource created      |
| `400 BAD REQUEST`           | Invalid request                |
| `404 NOT FOUND`             | Resource not found             |
| `500 INTERNAL SERVER ERROR` | Server-side error              |

---

# 🗄️ Database Configuration

Configure your database in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_resource_system
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format_sql=true
```

Create the database before running the application:

```sql
CREATE DATABASE library_resource_system;
```

---

# ▶️ How to Run the Project

### Step 1 — Clone the Repository

```bash
git clone <your-github-repository-url>
```

### Step 2 — Open the Project

Open the project using:

* IntelliJ IDEA
* Eclipse
* Spring Tool Suite

### Step 3 — Configure MySQL

Create the database:

```sql
CREATE DATABASE library_resource_system;
```

Update your username and password in `application.properties`.

### Step 4 — Build the Project

Using Maven:

```bash
mvn clean install
```

### Step 5 — Run the Application

Run the main Spring Boot application class.

The application will start at:

```text
http://localhost:8080
```

### Step 6 — Test APIs

You can test the APIs using:

* Postman
* Thunder Client
* Insomnia
* Browser for GET requests

---

# 🧪 API Testing

Postman can be used to test all CRUD and search operations.

### Example Workflow

```text
1. POST /api/book
       ↓
2. GET /api/book
       ↓
3. GET /api/book/1
       ↓
4. PATCH /api/book/1
       ↓
5. PUT /api/book
       ↓
6. GET /api/book/genre/Programming
       ↓
7. DELETE /api/book/1
```

---

# 🎯 Project Objectives

The main objectives of this project are:

* To understand REST API development using Spring Boot.
* To implement CRUD operations.
* To understand Spring Data JPA.
* To work with repository query methods.
* To implement layered architecture.
* To understand HTTP methods and status codes.
* To practice API testing using Postman.
* To implement filtering/search functionality.
* To work with MySQL database integration.

---

# 🔮 Future Enhancements

The project can be extended with:

* 👤 User registration and login
* 🔐 Spring Security and JWT authentication
* 👨‍💼 Admin and user roles
* 📚 Book borrowing and returning
* 📅 Due-date management
* 💰 Fine calculation
* 🔍 Advanced book search
* 📄 Pagination and sorting
* 📊 Library dashboard
* 📧 Email notifications
* 📝 Book reviews and ratings
* 📖 Book reservation functionality
* 🗃️ Category management
* 🧑‍💼 Librarian management

---

# 👨‍💻 Developer

**Sajal Sahu**

Java Backend Developer | Spring Boot | REST API | MySQL

### Skills Demonstrated

```text
Java
Spring Boot
Spring Data JPA
REST API
Hibernate
MySQL
Maven
Postman
Git & GitHub
```

---

# ⭐ Project Highlights

This project demonstrates practical knowledge of:

```text
✔ RESTful API Development
✔ CRUD Operations
✔ Spring Boot
✔ Spring Data JPA
✔ Hibernate
✔ MySQL
✔ Repository Query Methods
✔ PUT & PATCH Operations
✔ Layered Architecture
✔ DTO-based Response Handling
✔ API Testing
```

---

## 📌 Conclusion

The **Library Resource System** is a Spring Boot REST API project designed to manage library book resources.

It provides complete CRUD functionality along with multiple search and filtering APIs, making it a useful backend project for demonstrating **Java, Spring Boot, JPA, Hibernate, REST API, and MySQL** skills.

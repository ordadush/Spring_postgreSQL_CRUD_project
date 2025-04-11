# CRUD Application with Spring

This is a CRUD (Create, Read, Update, Delete) application built with **Spring Boot**. 
It provides a simple way to interact with a relational database and perform basic CRUD operations. 
The purpose of this project is to practice backend development and integrate Spring Boot with a database.

## Features

- **Create**: Add new records to the database.
- **Read**: View records from the database.
- **Update**: Modify existing records.
- **Delete**: Remove records from the database.

## Technologies Used

- **Spring Boot**: A framework to create stand-alone, production-grade Spring-based applications.
- **Spring Data JPA**: Simplifies database access and management using Java Persistence API (JPA).
- **H2 Database**: In-memory database for quick development and testing.
- **Maven**: Dependency management and project build tool.

## Installation

1. Clone this repository:

   ```bash
   git clone https://github.com/yourusername/crud-spring.git

2. Navigate to the project directory:

    ```bash
   cd crud-spring

3. Build the project using Maven:

    ```bash
   mvn clean install

4. Run the application:

    ```bash
   mvn spring-boot:run

5. The application will be available at http://localhost:8080.


**Database Configuration**

By default, this project uses an H2 in-memory database. To configure a different database(e.g., MySQL, PostgreSQL), 
modify the application.properties file under src/main/resources.

For example, for MySQL:

spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
## Project Structure

src/ ├── main/ │ ├── java/ │ │ └── crud/ │ │ ├── controller/ │ │ ├── model/ │ │ ├── repository/ │ │ ├── service/ │ ├── resources/ │ │ ├── application.properties │ │ └── static/ │ │ └── templates/ └── test/


Controller: Manages HTTP requests and responses.

Model: Contains entity classes that represent data in the database.

Repository: Interfaces for database interaction using Spring Data JPA.

Service: Contains business logic related to CRUD operations.

**Contributing**

Fork this repository.

Create your feature branch (git checkout -b feature-name).

Commit your changes (git commit -am 'Add new feature').

Push to the branch (git push origin feature-name).

Open a Pull Request.

**License**

This project is licensed under the MIT License - see the LICENSE file for details.


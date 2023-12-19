# Library (Spring Boot Backend)

## Overview

This is a backend project for a Library Management System implemented using Spring Boot. The system is designed to manage the various aspects of a library, including book management, user authentication, and book categories.

## Features

- **Book Management**: Add, and fetch books from the library inventory.
- **Category Management**: Add, and fetch catigories from the library inventory.
- **User Authentication**: Secure authentication system for library users.

## Technologies Used

- **Spring Boot**: Framework for building robust Java-based backend applications.
- **Spring Security**: Provides authentication and authorization capabilities.
- **Spring Data JPA**: Simplifies database access and management.
- **MySQL**: Database for storing library data.
- **Swagger**: API documentation for easy understanding and testing.

## Setup Instructions

1. Clone the repository:

   ```bash
   git clone https://github.com/YahyaYousef/Library.git
   ```

2. Navigate to the project directory:

   ```bash
   cd demo
   ```

3. Configure the database connection in `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/library
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
   
4. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

   The application will be accessible at `http://localhost:8080`.

## API Documentation

Explore and test the API using Swagger UI:

- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Contributors

- [Yahya Yousef](https://github.com/YahyaYousef)

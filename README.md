# Customer Registration System

A Spring Boot web application for customer registration with comprehensive validation and modern UI design.

## Overview

This application provides a robust customer registration system built with Spring Boot, featuring real-time email validation, comprehensive form validation, and a responsive user interface. The system captures essential customer information and stores it securely in a database.

## Key Features

- **Customer Registration Form** with comprehensive validation
- **Real-time Email Availability Checking** via AJAX
- **Responsive Web Design** optimized for all devices
- **Database Persistence** with H2 (development) and MySQL (production) support
- **Input Validation** both client-side and server-side
- **Success Confirmation** with user-friendly messaging

## Technology Stack

### Backend

- **Java 17** - Programming language
- **Spring Boot 3.2.0** - Application framework
- **Spring Data JPA** - Data persistence layer
- **Spring MVC** - Web layer
- **Bean Validation** - Input validation

### Frontend

- **Thymeleaf** - Template engine
- **Bootstrap 5** - CSS framework
- **JavaScript** - Client-side functionality

### Database

- **H2 Database** - In-memory database for development
- **MySQL** - Production database support

### Build Tools

- **Maven** - Dependency management and build automation

## Database Schema

The application uses a `customers` table with the following structure:

| Field            | Type         | Required | Description                        |
| ---------------- | ------------ | -------- | ---------------------------------- |
| `id`             | BIGINT       | Yes      | Auto-generated primary key         |
| `email_address`  | VARCHAR(255) | Yes      | Unique email address               |
| `title`          | VARCHAR(5)   | Yes      | Customer title (Mr, Mrs, Dr, etc.) |
| `first_name`     | VARCHAR(50)  | Yes      | Customer's first name              |
| `last_name`      | VARCHAR(50)  | Yes      | Customer's last name               |
| `address_line_1` | VARCHAR(255) | Yes      | Primary address line               |
| `address_line_2` | VARCHAR(255) | No       | Secondary address line             |
| `city`           | VARCHAR(255) | No       | City                               |
| `postcode`       | VARCHAR(10)  | Yes      | Postal code                        |
| `phone_number`   | VARCHAR(20)  | No       | Phone number                       |
| `registered`     | DATETIME     | Yes      | Registration timestamp             |

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Installation and Setup

1. **Clone the repository:**

   ```bash
   git clone (https://github.com/muiz2353673/burns-Sheehan-assessment.git)
   cd burns-Sheehan-assessment
   ```

2. **Build the application:**

   ```bash
   mvn clean install
   ```

3. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

4. **Access the application:**
   - Main application: http://localhost:8080
   - Registration form: http://localhost:8080/registration
   - H2 Database Console: http://localhost:8080/h2-console

### H2 Database Configuration (Development)

For development and testing, the application uses H2 in-memory database:

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** `password`

## Application Architecture

The application follows a layered architecture pattern:

### Package Structure

```
src/main/java/com/aeroparker/registration/
├── RegistrationApplication.java     # Main application entry point
├── controller/
│   └── RegistrationController.java  # Web layer - handles HTTP requests
├── model/
│   └── Customer.java               # Entity layer - JPA entities
├── repository/
│   └── CustomerRepository.java     # Data access layer
└── service/
    └── CustomerService.java        # Business logic layer
```

### Key Components

- **RegistrationController** - Handles web requests and form processing
- **CustomerService** - Contains business logic and validation rules
- **CustomerRepository** - Data access operations with Spring Data JPA
- **Customer** - JPA entity representing customer data

## API Endpoints

| Method | Endpoint        | Description                        |
| ------ | --------------- | ---------------------------------- |
| GET    | `/`             | Redirects to registration form     |
| GET    | `/registration` | Displays registration form         |
| POST   | `/registration` | Processes form submission          |
| GET    | `/success`      | Shows registration success page    |
| GET    | `/check-email`  | AJAX endpoint for email validation |

## Features

### Form Validation

- **Client-side validation** using HTML5 and JavaScript
- **Server-side validation** using Bean Validation annotations
- **Real-time email availability checking** via AJAX

### Data Persistence

- **Unique email constraint** prevents duplicate registrations
- **Automatic timestamp generation** for registration time
- **Case-insensitive email handling** for consistency

### User Experience

- **Responsive design** works on all device sizes
- **Clear error messaging** guides users to correct input
- **Success confirmation** provides positive feedback

## Configuration

### Development (Default)

The application runs with H2 in-memory database by default. No additional configuration required.

### Production (MySQL)

To use MySQL in production, update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/registration_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=validate
```

## Testing

Run the test suite:

```bash
mvn test
```

The application includes basic integration tests to verify Spring context loading and core functionality.

## Technical Considerations

### Security

- Input validation prevents malicious data
- SQL injection protection through JPA parameterized queries
- XSS protection through proper output encoding

### Performance

- Database indexes on email and timestamp fields
- Efficient existence queries for email validation
- Optimized database queries through Spring Data JPA

### Scalability

- Stateless design supports horizontal scaling
- Database connection pooling for concurrent users
- Separation of concerns enables independent component scaling

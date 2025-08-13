package com.aeroparker.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * AeroParker Customer Registration Application
 * 
 * This is the main entry point for the AeroParker customer registration system.
 * It's a Spring Boot application that provides a web-based interface for
 * customer registration with the following features:
 * 
 * Key Features:
 * - Customer registration form with validation
 * - Real-time email availability checking
 * - Responsive web design with modern UI
 * - H2 in-memory database for development/testing
 * - MySQL support for production deployment
 * - RESTful endpoints for form processing and AJAX calls
 * 
 * Application Architecture:
 * - Controller Layer: Handles HTTP requests and responses
 * - Service Layer: Contains business logic and validation rules
 * - Repository Layer: Manages data access and persistence
 * - Model Layer: Defines entity structures and validation rules
 * - View Layer: Thymeleaf templates with Bootstrap styling
 * 
 * Technologies Used:
 * - Spring Boot 3.2.0 with Java 17
 * - Spring MVC for web layer
 * - Spring Data JPA for data persistence
 * - Bean Validation for input validation
 * - Thymeleaf for server-side template rendering
 * - Bootstrap 5 for responsive UI design
 * - H2/MySQL for database storage
 * 
 * Development Setup:
 * 1. Ensure Java 17+ is installed
 * 2. Run: mvn spring-boot:run
 * 3. Access application at: http://localhost:8080
 * 4. H2 console available at: http://localhost:8080/h2-console
 * 
 * @author AeroParker Development Team
 * @version 1.0
 * @since 2024-01-01
 */
@SpringBootApplication
public class RegistrationApplication {

    /**
     * Main method to start the Spring Boot application.
     * 
     * This method bootstraps the entire Spring Boot application, which includes:
     * - Auto-configuration of Spring components
     * - Embedded web server startup (Tomcat by default)
     * - Database connection initialization
     * - Component scanning and dependency injection
     * - Web MVC configuration and endpoint mapping
     * 
     * The application will be accessible at http://localhost:8080 once started.
     * 
     * @param args command line arguments (can be used for Spring Boot configuration)
     */
    public static void main(String[] args) {
        SpringApplication.run(RegistrationApplication.class, args);
    }
}

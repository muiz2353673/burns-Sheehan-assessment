package com.aeroparker.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Customer Registration Application
 * 
 * Main entry point for the customer registration system. This Spring Boot application
 * provides a web-based interface for customer registration with comprehensive validation,
 * real-time email availability checking, and database persistence.
 * 
 * Key Features:
 * - Customer registration form with server-side and client-side validation
 * - Real-time email availability checking via AJAX
 * - Responsive web design with modern UI
 * - Database persistence with H2 (development) and MySQL (production) support
 * - RESTful endpoints for form processing and API calls
 * 
 * @author AeroParker Development Team
 * @version 1.0
 * @since 2024-01-01
 */
@SpringBootApplication
public class RegistrationApplication {

    /**
     * Main method to bootstrap the Spring Boot application.
     * 
     * This method initializes the Spring Boot application context, which includes:
     * - Auto-configuration of Spring components
     * - Embedded web server startup (Tomcat by default)
     * - Database connection initialization
     * - Component scanning and dependency injection
     * - Web MVC configuration and endpoint mapping
     * 
     * The application will be accessible at http://localhost:8080 once started.
     * 
     * @param args command line arguments for application configuration
     */
    public static void main(String[] args) {
        SpringApplication.run(RegistrationApplication.class, args);
    }
}

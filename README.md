# AeroParker Customer Registration System

A Spring Boot web application for customer registration with form validation, database persistence, and modern UI.

## Features

### Core Requirements ✅
- **Spring Boot 3.2.0** with Java 17
- **MySQL/H2 Database** support
- **Customer Registration Form** accessible at `/registration`
- **Database Schema** with all required fields
- **Server-side Validation** with proper error handling
- **Success Page** after successful registration

### Enhanced Features 🚀
- **Case-insensitive Email Validation** - prevents duplicate emails regardless of case
- **Real-time Email Checking** - AJAX validation to check email availability
- **Client-side Validation** - immediate feedback on form fields
- **Modern UI** - Bootstrap 5 with custom styling
- **Responsive Design** - works on all device sizes
- **H2 Console** - database management interface at `/h2-console`

## Technology Stack

- **Backend**: Spring Boot 3.2.0, Java 17
- **Database**: H2 (development) / MySQL (production)
- **Frontend**: Thymeleaf, Bootstrap 5, JavaScript
- **Validation**: Bean Validation (JSR-303)
- **Build Tool**: Maven

## Database Schema

The application creates a `customers` table with the following structure:

| Column Name | Data Type | Mandatory | Constraints |
|-------------|-----------|-----------|-------------|
| id | INT | Y | Auto-incremented Primary Key |
| registered | DATETIME | Y | Timestamp of registration |
| email_address | VARCHAR(255) | Y | Unique, case-insensitive |
| title | VARCHAR(5) | Y | Mr, Mrs, Miss, Ms, Dr, Prof |
| first_name | VARCHAR(50) | Y | Customer's first name |
| last_name | VARCHAR(50) | Y | Customer's last name |
| address_line_1 | VARCHAR(255) | Y | Primary address |
| address_line_2 | VARCHAR(255) | N | Secondary address |
| city | VARCHAR(255) | N | City name |
| postcode | VARCHAR(10) | Y | Postal code |
| phone_number | VARCHAR(20) | N | Contact number |

## Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- MySQL Server (optional, H2 is used by default)

### Running the Application

1. **Clone or download the project**
   ```bash
   cd aeroparker-registration
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - Registration Form: http://localhost:8080/registration
   - H2 Console: http://localhost:8080/h2-console
   - Home Page: http://localhost:8080/ (redirects to registration)

### H2 Database Console
- URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

## Configuration

### Using H2 Database (Default)
The application is configured to use H2 in-memory database by default. No additional setup required.

### Using MySQL Database
1. **Update application.properties**
   ```properties
   # Comment out H2 configuration
   # spring.datasource.url=jdbc:h2:mem:testdb
   
   # Uncomment MySQL configuration
   spring.datasource.url=jdbc:mysql://localhost:3306/aeroparker?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
   ```

2. **Create MySQL database**
   ```sql
   CREATE DATABASE aeroparker;
   ```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Redirects to registration form |
| GET | `/registration` | Display registration form |
| POST | `/registration` | Submit registration form |
| GET | `/success` | Display success page |
| GET | `/check-email` | Check email availability (AJAX) |
| GET | `/h2-console` | H2 database console |

## Form Validation

### Server-side Validation
- **Email**: Valid email format, unique constraint
- **Title**: Required, max 5 characters
- **First Name**: Required, max 50 characters
- **Last Name**: Required, max 50 characters
- **Address Line 1**: Required, max 255 characters
- **Address Line 2**: Optional, max 255 characters
- **City**: Optional, max 255 characters
- **Postcode**: Required, max 10 characters
- **Phone Number**: Optional, max 20 characters

### Client-side Validation
- Real-time email availability checking
- Required field validation
- Input length restrictions
- Email format validation

## Project Structure

```
src/
├── main/
│   ├── java/com/aeroparker/registration/
│   │   ├── RegistrationApplication.java
│   │   ├── controller/
│   │   │   └── RegistrationController.java
│   │   ├── model/
│   │   │   └── Customer.java
│   │   ├── repository/
│   │   │   └── CustomerRepository.java
│   │   └── service/
│   │       └── CustomerService.java
│   └── resources/
│       ├── application.properties
│       └── templates/
│           ├── registration.html
│           └── success.html
└── test/
    └── java/
        └── com/aeroparker/registration/
            └── RegistrationApplicationTests.java
```

## Testing the Application

1. **Start the application**
2. **Navigate to** http://localhost:8080/registration
3. **Fill out the form** with test data
4. **Submit the form** and verify success page
5. **Check H2 console** to see saved data
6. **Try duplicate email** to test validation

## Deployment

### JAR Deployment
```bash
mvn clean package
java -jar target/registration-1.0.0.jar
```

### Docker Deployment (Optional)
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/registration-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## Troubleshooting

### Common Issues

1. **Port 8080 already in use**
   - Change port in `application.properties`: `server.port=8081`

2. **Database connection issues**
   - Check MySQL service is running
   - Verify database credentials
   - Ensure database exists

3. **Validation errors not showing**
   - Check browser console for JavaScript errors
   - Verify Thymeleaf template syntax

### Logs
Application logs are available at DEBUG level for troubleshooting:
```properties
logging.level.com.aeroparker=DEBUG
logging.level.org.springframework.web=DEBUG
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is created for the AeroParker coding exercise.

## Support

For questions or issues, please contact the development team.

---

**Note**: This application is configured to use H2 database by default for easy setup and testing. For production use, switch to MySQL by updating the configuration in `application.properties`.

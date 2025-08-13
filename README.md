# Burns Sheehan Talent Registration System

A modern Spring Boot web application for talent registration, built with professional recruitment industry standards. This system captures comprehensive talent information with robust validation and provides an exceptional user experience.

Based on the comprehensive design and recruitment expertise of [Burns Sheehan](https://www.burnssheehan.co.uk), a leading technology recruitment consultancy specializing in AI & Data, Software Engineering, Product & Design, Platform & Infrastructure, and C-Suite leadership roles.

## 🎯 Overview

Burns Sheehan stands at the forefront of technology recruitment, building industry-leading teams across all sectors in tech. With deep expertise in hiring across data and leadership roles, they understand how to find the right talent that truly moves businesses forward.

This registration system embodies their commitment to:

- **Quality over Quantity**: Delivering high-quality candidates with impressive hit rates
- **Partnership Approach**: Taking time to understand business needs and values
- **Market Intelligence**: Leveraging deep market knowledge and insights
- **Professional Excellence**: Maintaining top-notch NPS scores and client satisfaction

## ✨ Features

### Core Registration Capabilities

- **Comprehensive Talent Profiling** - Capture detailed candidate information
- **Real-time Validation** - Instant feedback on email availability and form validation
- **Professional UI/UX** - Burns Sheehan branded interface with modern design
- **Mobile Responsive** - Optimized for all devices and screen sizes
- **Database Persistence** - Secure storage with MySQL/H2 support

### Technical Excellence

- **Spring Boot 3.2.0** with Java 17 (Latest LTS)
- **Advanced Validation** - Both client-side and server-side validation
- **Email Uniqueness** - Case-insensitive duplicate prevention
- **Glass-morphism Design** - Modern UI with professional aesthetics
- **Accessibility Compliant** - WCAG guidelines adherence
- **Performance Optimized** - Fast load times and efficient processing

### Burns Sheehan Integration

- **Brand-Aligned Design** - Professional orange/black color scheme
- **Industry Context** - Talent-focused language and terminology
- **Recruitment Workflow** - Built for recruitment industry processes
- **Professional Communication** - Industry-appropriate messaging

## 🛠 Technology Stack

### Backend Technologies

- **Java 17** - Latest LTS version for enterprise stability
- **Spring Boot 3.2.0** - Modern framework with latest features
- **Spring Data JPA** - Simplified database operations
- **Hibernate** - Advanced ORM capabilities
- **Bean Validation** - Comprehensive input validation

### Frontend Technologies

- **Thymeleaf** - Server-side template engine
- **Bootstrap 5** - Modern CSS framework
- **Custom CSS** - Burns Sheehan brand styling
- **JavaScript ES6+** - Modern client-side functionality
- **Font Awesome** - Professional iconography

### Database Support

- **H2 Database** - In-memory database for development
- **MySQL 8.0+** - Production-ready relational database
- **Connection Pooling** - HikariCP for optimal performance

### Build & Deployment

- **Maven 3.9+** - Reliable build automation
- **Spring Boot Maven Plugin** - Streamlined packaging
- **Docker Ready** - Container deployment support
- **Cloud Compatible** - Ready for AWS, GCP, Azure

## 📊 Database Schema

The system captures comprehensive talent information:

| Field            | Type         | Required | Description                            |
| ---------------- | ------------ | -------- | -------------------------------------- |
| `id`             | INT          | ✅       | Auto-generated unique identifier       |
| `registered`     | DATETIME     | ✅       | Registration timestamp                 |
| `email_address`  | VARCHAR(255) | ✅       | Unique email (case-insensitive)        |
| `title`          | VARCHAR(5)   | ✅       | Professional title (Mr, Mrs, Dr, etc.) |
| `first_name`     | VARCHAR(50)  | ✅       | Candidate's first name                 |
| `last_name`      | VARCHAR(50)  | ✅       | Candidate's surname                    |
| `address_line_1` | VARCHAR(255) | ✅       | Primary address                        |
| `address_line_2` | VARCHAR(255) | ❌       | Additional address info                |
| `city`           | VARCHAR(255) | ❌       | City/location                          |
| `postcode`       | VARCHAR(10)  | ✅       | Postal code                            |
| `phone_number`   | VARCHAR(20)  | ❌       | Contact number                         |

### Performance Features

- **Indexed Columns** - Email, registration date, and surname for fast queries
- **Unique Constraints** - Email uniqueness enforced at database level
- **Optimized Queries** - Efficient data retrieval patterns

## 🚀 Quick Start

### Prerequisites

- **Java 17+** - Download from [OpenJDK](https://openjdk.org/)
- **Maven 3.6+** - Build automation tool
- **MySQL 8.0+** - Optional for production (H2 included for development)

### Installation & Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/muiz2353673/burns-Sheehan-assessment.git
   cd burns-Sheehan-assessment
   ```

2. **Build the Application**

   ```bash
   mvn clean install
   ```

3. **Run the Application**

   ```bash
   mvn spring-boot:run
   ```

4. **Access the System**
   - **Registration Portal**: http://localhost:8080/registration
   - **Database Console**: http://localhost:8080/h2-console
   - **Home**: http://localhost:8080/ (redirects to registration)

### H2 Database Console (Development)

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`

## ⚙️ Configuration

### Development Environment (Default)

Uses H2 in-memory database with auto-configuration. No additional setup required.

### Production Environment (MySQL)

1. **Update `application.properties`**:

   ```properties
   # MySQL Configuration
   spring.datasource.url=jdbc:mysql://localhost:3306/burnssheehan_talent?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
   spring.jpa.hibernate.ddl-auto=validate
   ```

2. **Initialize Database**:
   ```bash
   mysql -u root -p < database-setup.sql
   ```

## 🔗 API Endpoints

| Method | Endpoint        | Description              | Response                              |
| ------ | --------------- | ------------------------ | ------------------------------------- |
| `GET`  | `/`             | Home redirect            | 302 → `/registration`                 |
| `GET`  | `/registration` | Registration form        | HTML form page                        |
| `POST` | `/registration` | Submit registration      | Success redirect or validation errors |
| `GET`  | `/success`      | Success confirmation     | HTML success page                     |
| `GET`  | `/check-email`  | Email availability check | "exists" or "available"               |

### Response Formats

- **HTML Pages**: Thymeleaf-rendered responsive pages
- **AJAX Responses**: Plain text for email validation
- **Error Handling**: User-friendly error messages with field-specific feedback

## 🔒 Security & Validation

### Input Validation

- **Email Format**: RFC-compliant email validation
- **Length Restrictions**: Prevents buffer overflow attacks
- **Required Fields**: Ensures data completeness
- **SQL Injection Prevention**: Parameterized queries with JPA

### Data Protection

- **Email Normalization**: Stored in lowercase for consistency
- **Unique Constraints**: Database-level duplicate prevention
- **Error Handling**: No sensitive information disclosure
- **Secure Logging**: No sensitive data in logs

### Recommended Enhancements

- **HTTPS/TLS**: SSL certificate configuration
- **Rate Limiting**: Prevent spam registrations
- **CSRF Protection**: Spring Security integration
- **Input Sanitization**: XSS prevention

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/aeroparker/registration/
│   │   ├── RegistrationApplication.java           # Main application
│   │   ├── controller/
│   │   │   └── RegistrationController.java        # Web endpoints
│   │   ├── model/
│   │   │   └── Customer.java                      # Entity definition
│   │   ├── repository/
│   │   │   └── CustomerRepository.java            # Data access
│   │   └── service/
│   │       └── CustomerService.java               # Business logic
│   └── resources/
│       ├── application.properties                 # Configuration
│       └── templates/
│           ├── registration.html                  # Registration form
│           └── success.html                       # Success page
├── test/
│   └── java/com/aeroparker/registration/
│       └── RegistrationApplicationTests.java      # Application tests
├── database-setup.sql                             # MySQL schema
├── PROJECT_DOCUMENTATION.txt                      # Detailed documentation
└── README.md                                      # This file
```

## 🧪 Testing

### Manual Testing

1. **Start Application**: `mvn spring-boot:run`
2. **Open Browser**: Navigate to http://localhost:8080/registration
3. **Test Registration**: Fill form with valid data
4. **Verify Success**: Check success page displays
5. **Database Verification**: Use H2 console to verify data
6. **Test Validation**: Try duplicate email, empty fields

### Automated Testing

```bash
# Run all tests
mvn test

# Run with coverage
mvn test jacoco:report
```

### Test Scenarios

- ✅ Valid registration data
- ✅ Email format validation
- ✅ Required field validation
- ✅ Duplicate email prevention
- ✅ Form submission and redirect
- ✅ Database persistence

## 🚢 Deployment

### Local JAR Deployment

```bash
# Build executable JAR
mvn clean package

# Run standalone
java -jar target/registration-1.0.0.jar
```

### Docker Deployment (Optional)

```dockerfile
FROM openjdk:17-jre-slim
COPY target/registration-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

```bash
# Build and run
docker build -t burns-sheehan-registration .
docker run -p 8080:8080 burns-sheehan-registration
```

### Cloud Deployment

- **AWS Elastic Beanstalk**: Upload JAR file
- **Google Cloud Platform**: App Engine or Cloud Run
- **Microsoft Azure**: App Service
- **Heroku**: Git-based deployment with Procfile

## 🐛 Troubleshooting

### Common Issues

**Port Already in Use**

```bash
# Find process using port 8080
lsof -ti:8080 | xargs kill -9

# Or change port
echo "server.port=8081" >> src/main/resources/application.properties
```

**Database Connection Issues**

- Verify MySQL service: `sudo service mysql status`
- Check credentials in `application.properties`
- Ensure database exists: `CREATE DATABASE burnssheehan_talent;`

**Memory Issues**

```bash
# Increase JVM heap size
export MAVEN_OPTS="-Xmx1024m"
mvn spring-boot:run
```

**Build Failures**

```bash
# Clean and rebuild
mvn clean install -DskipTests

# Update dependencies
mvn versions:use-latest-versions
```

### Debug Mode

Enable detailed logging:

```properties
logging.level.com.aeroparker=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.springframework.security=DEBUG
```

## 📈 Performance Monitoring

### Key Metrics

- **Page Load Time**: < 2 seconds
- **Form Submission**: < 500ms
- **Email Validation**: < 300ms
- **Database Queries**: < 100ms


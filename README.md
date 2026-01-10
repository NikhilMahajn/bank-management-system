# Banking Management System - Backend

> A secure and scalable RESTful API for comprehensive banking operations built with Spring Boot

## [Live Link](https://bank-system-fe-seven.vercel.app/)

## Overview

The Banking Management System Backend is a robust, enterprise-grade application that provides complete banking functionality through a well-structured REST API. It implements secure authentication, role-based authorization, and comprehensive transaction management. The system supports multiple user roles (Admin, Employee, Customer) with specific permissions and features for each role.

This backend serves as the foundation for modern banking operations, handling everything from account management to complex financial transactions with security and reliability at its core.

## Features

### Authentication & Authorization
- **JWT-based Authentication** - Secure token-based authentication system
- **Role-Based Access Control (RBAC)** - Three distinct roles: Admin, Employee, Customer
- **Spring Security Integration** - Industry-standard security implementation
- **Password Encryption** - BCrypt password hashing for secure storage

### Account Management
- Create and manage customer bank accounts
- Support for multiple account types (Savings, Current, etc.)
- Account activation/deactivation
- Balance inquiry and account statements
- Account holder information management

### Transaction Operations
- **Deposits** - Add funds to customer accounts
- **Withdrawals** - Secure cash withdrawal with validation
- **Fund Transfers** - Inter-account and inter-branch transfers
- **Transaction History** - Comprehensive audit trail of all transactions
- **Transaction Validation** - Real-time balance checks and fraud prevention

### Branch Management
- Create and manage bank branches
- Branch-wise customer and employee assignment
- Branch performance tracking
- Multi-branch support for enterprise deployment

### Employee Management
- Employee registration and profile management
- Role assignment and permission control
- Employee-customer relationship tracking
- Department and designation management

### Administrative Features
- System-wide reporting and analytics
- User management and access control
- Audit logs and compliance tracking
- System configuration and settings

## Tech Stack

| Technology | Purpose |
|------------|---------|
| **Java 17+** | Programming language |
| **Spring Boot 3.x** | Application framework |
| **Spring Security** | Authentication & authorization |
| **Spring Data JPA** | Data persistence layer |
| **Hibernate** | ORM framework |
| **PostgreSQL / MySQL** | Relational database |
| **JWT (JSON Web Tokens)** | Stateless authentication |
| **Maven** | Dependency management |
| **Lombok** | Boilerplate code reduction |
| **ModelMapper** | Object mapping |
| **Validation API** | Input validation |

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK) 17 or higher**
  - Verify: `java -version`
- **Maven 3.6+**
  - Verify: `mvn -version`
- **PostgreSQL 13+** or **MySQL 8+**
  - Running database instance
- **Git** (for cloning the repository)
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code recommended)

## Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/banking-management-backend.git
cd banking-management-backend
```

### 2. Configure Database

Create a new database for the application:

**PostgreSQL:**
```sql
CREATE DATABASE banking_system;
CREATE USER banking_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE banking_system TO banking_user;
```

**MySQL:**
```sql
CREATE DATABASE banking_system;
CREATE USER 'banking_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON banking_system.* TO 'banking_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configure Environment Variables

Create an `application.properties` or `application.yml` file in `src/main/resources/`:

**application.properties:**
```properties
# Server Configuration
server.port=8080

# Database Configuration (PostgreSQL)
spring.datasource.url=jdbc:postgresql://localhost:5432/banking_system
spring.datasource.username=banking_user
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# Database Configuration (MySQL - Alternative)
# spring.datasource.url=jdbc:mysql://localhost:3306/banking_system
# spring.datasource.username=banking_user
# spring.datasource.password=your_password
# spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
# spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# JWT Configuration
jwt.secret=your-secret-key-min-256-bits-for-hs256-algorithm
jwt.expiration=86400000
jwt.refresh-expiration=604800000

# Application Configuration
app.name=Banking Management System
app.version=1.0.0

# Logging
logging.level.root=INFO
logging.level.com.banking=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
```

### 4. Install Dependencies

```bash
mvn clean install
```

### 5. Run Database Migrations

The application uses Hibernate's `ddl-auto=update` to automatically create/update tables. For production, consider using Flyway or Liquibase for controlled migrations.

### 6. Build the Application

```bash
mvn clean package
```

## Environment Variables

| Variable | Description | Example | Required |
|----------|-------------|---------|----------|
| `SPRING_DATASOURCE_URL` | Database connection URL | `jdbc:postgresql://localhost:5432/banking_system` | Yes |
| `SPRING_DATASOURCE_USERNAME` | Database username | `banking_user` | Yes |
| `SPRING_DATASOURCE_PASSWORD` | Database password | `securePassword123` | Yes |
| `JWT_SECRET` | Secret key for JWT signing | `your-256-bit-secret-key` | Yes |
| `JWT_EXPIRATION` | JWT token expiration (ms) | `86400000` (24 hours) | No |
| `SERVER_PORT` | Application port | `8080` | No |

**Setting environment variables (Linux/Mac):**
```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/banking_system
export SPRING_DATASOURCE_USERNAME=banking_user
export SPRING_DATASOURCE_PASSWORD=your_password
export JWT_SECRET=your-secret-key-min-256-bits
```

**Setting environment variables (Windows):**
```cmd
set SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/banking_system
set SPRING_DATASOURCE_USERNAME=banking_user
set SPRING_DATASOURCE_PASSWORD=your_password
set JWT_SECRET=your-secret-key-min-256-bits
```

## Folder Structure

```
banking-management-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── banking/
│   │   │           ├── BankingApplication.java
│   │   │           ├── config/
│   │   │           │   ├── SecurityConfig.java
│   │   │           │   ├── JwtConfig.java
│   │   │           │   └── CorsConfig.java
│   │   │           ├── controller/
│   │   │           │   ├── AuthController.java
│   │   │           │   ├── AccountController.java
│   │   │           │   ├── TransactionController.java
│   │   │           │   ├── BranchController.java
│   │   │           │   ├── EmployeeController.java
│   │   │           │   └── CustomerController.java
│   │   │           ├── dto/
│   │   │           │   ├── request/
│   │   │           │   │   ├── LoginRequest.java
│   │   │           │   │   ├── RegisterRequest.java
│   │   │           │   │   ├── TransactionRequest.java
│   │   │           │   │   └── TransferRequest.java
│   │   │           │   └── response/
│   │   │           │       ├── AuthResponse.java
│   │   │           │       ├── AccountResponse.java
│   │   │           │       └── TransactionResponse.java
│   │   │           ├── models/
│   │   │           │   ├── User.java
│   │   │           │   ├── Account.java
│   │   │           │   ├── Transaction.java
│   │   │           │   ├── Branch.java
│   │   │           │   ├── Employee.java
│   │   │           │   └── Customer.java
│   │   │           ├── repository/
│   │   │           │   ├── UserRepository.java
│   │   │           │   ├── AccountRepository.java
│   │   │           │   ├── TransactionRepository.java
│   │   │           │   ├── BranchRepository.java
│   │   │           │   ├── EmployeeRepository.java
│   │   │           │   └── CustomerRepository.java
│   │   │           ├── service/
│   │   │           │   ├── AuthService.java
│   │   │           │   ├── AccountService.java
│   │   │           │   ├── TransactionService.java
│   │   │           │   ├── BranchService.java
│   │   │           │   ├── EmployeeService.java
│   │   │           │   └── CustomerService.java
│   │   │           ├── security/
│   │   │           │   ├── JwtTokenProvider.java
│   │   │           │   ├── JwtAuthenticationFilter.java
│   │   │           │   └── CustomUserDetailsService.java
│   │   │           ├── exception/
│   │   │               ├── GlobalExceptionHandler.java
│   │   │               ├── ResourceNotFoundException.java
│   │   │               ├── InsufficientBalanceException.java
│   │   │               └── UnauthorizedException.java
│   │   │          
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       ├── application-prod.properties
│   │       └── static/
│   └── test/
│       └── java/
│           └── com/
│               └── banking/
│                   ├── controller/
│                   ├── service/
│                   └── repository/
├── target/
├── .gitignore
├── pom.xml
├── README.md
└── Dockerfile
```

## Run Commands

### Development Mode

```bash
# Using Maven
mvn spring-boot:run

# Using Maven with specific profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Using Java
mvn clean package
java -jar target/banking-management-system-0.0.1-SNAPSHOT.jar
```

### Production Build

```bash
# Build the application
mvn clean package -DskipTests

# Run the JAR file
java -jar target/banking-management-system-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

### Testing

```bash
# Run all tests
mvn test

# Run tests with coverage
mvn clean test jacoco:report

# Run specific test class
mvn test -Dtest=AccountServiceTest
```

### Running with Environment Variables

```bash
java -jar target/banking-management-system-0.0.1-SNAPSHOT.jar \
  --spring.datasource.url=jdbc:postgresql://localhost:5432/banking_system \
  --spring.datasource.username=banking_user \
  --spring.datasource.password=your_password \
  --jwt.secret=your-secret-key
```

## Docker Instructions (Optional)

### Dockerfile

```dockerfile
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package -DskipTests
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
```

### docker-compose.yml

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:15-alpine
    container_name: banking-postgres
    environment:
      POSTGRES_DB: banking_system
      POSTGRES_USER: banking_user
      POSTGRES_PASSWORD: banking_password
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    networks:
      - banking-network

  backend:
    build: .
    container_name: banking-backend
    depends_on:
      - postgres
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/banking_system
      SPRING_DATASOURCE_USERNAME: banking_user
      SPRING_DATASOURCE_PASSWORD: banking_password
      JWT_SECRET: your-secret-key-min-256-bits-for-production
    ports:
      - "8080:8080"
    networks:
      - banking-network

volumes:
  postgres_data:

networks:
  banking-network:
    driver: bridge
```

### Docker Commands

```bash
# Build the Docker image
docker build -t banking-backend:latest .

# Run with Docker Compose
docker-compose up -d

# View logs
docker-compose logs -f backend

# Stop services
docker-compose down

# Rebuild and restart
docker-compose up -d --build
```

## API Documentation (Swagger)

Once the application is running, access the interactive API documentation:

**Swagger UI:** `http://localhost:8080/swagger-ui/index.html`

**OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

### Key API Endpoints

#### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login
- `POST /api/auth/refresh` - Refresh JWT token

#### Accounts
- `GET /api/accounts` - Get all accounts (Admin/Employee)
- `GET /api/accounts/{id}` - Get account by ID
- `POST /api/accounts` - Create new account
- `PUT /api/accounts/{id}` - Update account
- `DELETE /api/accounts/{id}` - Delete account

#### Transactions
- `POST /api/transactions/deposit` - Deposit funds
- `POST /api/transactions/withdraw` - Withdraw funds
- `POST /api/transactions/transfer` - Transfer funds
- `GET /api/transactions/history/{accountId}` - Get transaction history

#### Branches
- `GET /api/branches` - Get all branches
- `POST /api/branches` - Create branch (Admin only)
- `PUT /api/branches/{id}` - Update branch
- `DELETE /api/branches/{id}` - Delete branch

#### Employees
- `GET /api/employees` - Get all employees
- `POST /api/employees` - Add employee (Admin only)
- `PUT /api/employees/{id}` - Update employee
- `GET /api/employees/{id}/customers` - Get employee's customers

## Contributing

We welcome contributions to the Banking Management System! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. **Commit your changes**
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```
4. **Push to the branch**
   ```bash
   git push origin feature/AmazingFeature
   ```
5. **Open a Pull Request**

### Coding Standards
- Follow Java naming conventions
- Write unit tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting PR
- Follow Spring Boot best practices

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2024 Banking Management System

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

## Future Enhancements

### Planned Features
- [ ] **Multi-Currency Support** - Handle transactions in multiple currencies with real-time exchange rates
- [ ] **Loan Management** - Complete loan application, approval, and repayment system
- [ ] **Credit/Debit Card Management** - Card issuance, activation, and transaction processing
- [ ] **Two-Factor Authentication (2FA)** - Enhanced security with OTP and authenticator apps
- [ ] **Email Notifications** - Transaction alerts and account updates via email
- [ ] **SMS Banking** - SMS-based transaction notifications and balance inquiry
- [ ] **Scheduled Transfers** - Recurring payments and standing instructions
- [ ] **Bill Payment Integration** - Utility bill payments and vendor management
- [ ] **Fixed Deposit Management** - FD creation, renewal, and maturity tracking
- [ ] **Advanced Analytics Dashboard** - Real-time insights and reporting for administrators
- [ ] **Audit Trail Enhancement** - Comprehensive logging and compliance reporting
- [ ] **KYC Document Management** - Digital document upload and verification
- [ ] **Microservices Architecture** - Split monolith into microservices for scalability
- [ ] **Redis Caching** - Improve performance with distributed caching
- [ ] **GraphQL API** - Alternative API interface for flexible queries
- [ ] **Webhook Support** - Real-time event notifications for third-party integrations
- [ ] **Rate Limiting** - API rate limiting to prevent abuse
- [ ] **Elasticsearch Integration** - Advanced search and analytics capabilities

### Technical Improvements
- [ ] CI/CD Pipeline setup with GitHub Actions/Jenkins
- [ ] Kubernetes deployment manifests
- [ ] Performance monitoring with Prometheus and Grafana
- [ ] Enhanced security with OAuth 2.0
- [ ] Database migration management with Flyway
- [ ] Comprehensive integration tests
- [ ] API versioning strategy
- [ ] Improved error handling and custom exception hierarchy

---

**Built with ❤️ using Spring Boot By Nikhil Mahajan**

For questions or support, please open an issue or contact the development team.
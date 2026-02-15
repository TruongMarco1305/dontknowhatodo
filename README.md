# Don't Know What to Do

> ["Don't know what to do" 🖤🩷](https://youtu.be/bqzDuRz_P7g?si=e2ohdFvOprGlAtDc) — Because sometimes you really don't know what to do with your tasks!

A modern Spring Boot backend application for a todo management system. This project provides a robust API for users to manage their tasks, with secure authentication and user profile management.

---

## Table of Contents

- [Prerequisites](#prerequisites)
- [Features](#features)
- [Project Structure](#project-structure)
- [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)
- [Configuration](#configuration)

---

## Prerequisites

Before running this project, ensure you have the following installed:

- **Java 25** or higher
- **Apache Maven 3.8.0** or higher
- **PostgreSQL 12** or higher (for database)

Check your versions:
```bash
java -version
mvn -version
```

---

## Features

### Authentication & Authorization
- **User Registration**: Sign up with email and password
- **User Login**: Authenticate with credentials
- **Logout**: Secure session termination

### User Management
- **User Profile Management**: View and update user information
- **User Details**: Access user profile with roles and permissions
- **Profile Update**: Modify user name, bio, company, and avatar

---

## Project Structure

```
dontknowhatodo/
├── src/
│   ├── main/
│   │   ├── java/io/github/dontknowhatodo/
│   │   │   ├── analyzer/                 # Startup failure analysis
│   │   │   ├── auth/                     # Authentication logic
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── AuthService.java
│   │   │   │   ├── JwtAuthFilter.java
│   │   │   │   ├── JwtService.java
│   │   │   │   └── dto/
│   │   │   ├── config/                   # Spring configuration
│   │   │   │   ├── BaseEntityConfig.java
│   │   │   │   ├── CorsConfig.java
│   │   │   │   ├── JpaConfig.java
│   │   │   │   ├── PasswordConfig.java
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── exception/                # Exception handling
│   │   │   │   └── GraphQLExceptionHandler.java
│   │   │   ├── user/                     # User management
│   │   │   │   ├── UserController.java
│   │   │   │   ├── UserInfoService.java
│   │   │   │   ├── UserInfoRepository.java
│   │   │   │   ├── UserInfo.java
│   │   │   │   └── dto/
│   │   │   └── DontknowhatodoApplication.java
│   │   └── resources/
│   │       ├── application.properties     # Main configuration
│   │       ├── application-local.properties
│   │       ├── application-production.properties
│   │       └── graphql/
│   │           └── schema.graphqls       # GraphQL schema
│   └── test/                             # Unit tests
│       └── java/io/github/dontknowhatodo/
└── pom.xml                               # Maven dependencies
```

---

## Technology Stack

### Core Framework
- **Spring Boot 4.0.2**: Full-stack framework for building REST APIs
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: ORM and data persistence
- **Spring GraphQL**: Modern GraphQL API support
- **Spring Modulith**: Modular architecture support

### Authentication & Security
- **JWT (JSON Web Tokens)**: Token-based authentication (jjwt 0.11.5)
- **BCrypt**: Password encryption algorithm
- **Spring Security OAuth2 Client**: OAuth2 integration support

### Database
- **PostgreSQL**: Production-grade relational database
- **Hibernate**: ORM framework via Spring Data JPA

### Development Tools
- **Lombok**: Reduce boilerplate code with annotations
- **Spring DevTools**: Hot reload and live reload support
- **GraphQL Code Generator**: Maven plugin for type-safe GraphQL clients

### Testing
- **Spring Boot Test**: Integrated testing framework
- **JUnit**: Unit testing framework
- **GraphQL Testing**: GraphQL-specific test support

### Build & Dependency Management
- **Apache Maven 3.8+**: Build automation and dependency management
- **Java 25**: Latest Java features and performance improvements

---

## Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd dontknowhatodo
```

### 2. Setup Environment Variables

#### Create `.env` File from Template

```bash
cp .env.example .env
```

#### Configure `.env` File

Open the newly created `.env` file and fill in the required environment variables:

```bash
# Application Profile (local or production)
PROFILE=local

# Database Configuration
DB_URL=jdbc:postgresql://localhost:5432/dontknowhatodo
DB_PASSWORD=your_secure_password

# JPA/Hibernate Configuration
# Options: create, create-drop, update, validate, none
JPA_DDL_AUTO=update

# OAuth2 Google Configuration (Optional)
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret

# JWT Configuration
JWT_SECRET=your_super_secret_jwt_key_min_32_characters_long
JWT_EXPIRATION=86400000

# Frontend Configuration
FRONTEND_URL=http://localhost:3000
FRONTEND_REDIRECT_URI=http://localhost:3000/callback

# GraphQL Configuration
GRAPHIQL_ENABLED=true
```

#### Environment Variable Guide

| Variable | Description | Example |
|----------|-------------|---------|
| `PROFILE` | Active Spring profile | `local`, `production` |
| `DB_URL` | PostgreSQL JDBC connection string | `jdbc:postgresql://localhost:5432/dontknowhatodo` |
| `DB_PASSWORD` | Database password | `your_secure_password` |
| `JPA_DDL_AUTO` | Hibernate DDL auto strategy | `update`, `create`, `validate` |
| `GOOGLE_CLIENT_ID` | Google OAuth2 Client ID | Get from Google Cloud Console |
| `GOOGLE_CLIENT_SECRET` | Google OAuth2 Client Secret | Get from Google Cloud Console |
| `JWT_SECRET` | Secret key for JWT signing (min 32 chars) | Generate with: `openssl rand -hex 32` |
| `JWT_EXPIRATION` | Token expiration time in milliseconds | `86400000` (24 hours) |
| `FRONTEND_URL` | Frontend application URL | `http://localhost:3000` |
| `FRONTEND_REDIRECT_URI` | OAuth2 redirect URI | `http://localhost:3000/callback` |
| `GRAPHIQL_ENABLED` | Enable GraphQL playground | `true`, `false` |



### 3. Build the Project
```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```


### 5. Access the Application

- **GraphQL Playground**: http://localhost:8080/graphiql
- **REST API**: http://localhost:8080/api/auth/...

---

## Configuration

### Database Configuration
The application uses PostgreSQL. Ensure your database is running and connection details are properly configured in the application properties.

### Security Configuration
- JWT expiration time can be configured via properties
- CORS settings can be customized in `CorsConfig.java`
- Password encoding algorithm is BCrypt (configurable in `PasswordConfig.java`)

### Active Profiles
- `local`: Development environment with relaxed settings
- `production`: Production environment with strict security

---

## Contributing

1. Create a feature branch
2. Commit your changes
3. Push to the branch
4. Open a pull request

---

## License

This project is open source and available under the MIT License.

---

## Support & Issues

For bug reports and feature requests, please open an issue on GitHub.

---

**Made by a Blink 🖤🩷**

*P.S. — When you really don't know what to do with your tasks, just add them here!*

# Event Ticket Platform - Backend

A Spring Boot REST API backend for an event ticket management platform.

## Overview

This is the backend service for an Event Ticket Platform that handles event management, ticket booking, user authentication, and payment processing. Built with Spring Boot and designed to be scalable and secure.

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

## 🏃‍♂️ Getting Started

### Clone the Repository
```bash
git clone <repository-url>
cd backend
```

### Build the Project
```bash
./mvnw clean install
```

### Run the Application
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/raid/tickets/
│   │       └── BackendApplication.java
│   └── resources/
│       ├── application.yaml
│       ├── static/
│       └── templates/
└── test/
    └── java/
        └── com/raid/tickets/
            └── BackendApplicationTests.java
```

## 🏗️ Architecture

### Class Diagram

![Class Diagram](docs/images/class-diagram.png)

*System architecture showing entity relationships*

**Note**: This is a work in progress. Features and documentation will be updated as the project evolves.

# NextStep | Graduation Project

![NextStep Logo](src/main/resources/logo/logo.png)

NextStep is an application that facilitates home construction and finishing, developed as a Graduation Project from January 2023 to June 2023.

## Table of Contents
- [Introduction](#introduction)
- [Technology](#technology)
- [Features](#features)
- [Setup](#setup)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Introduction

NextStep is a comprehensive platform designed to streamline the process of home construction and finishing. It caters to four main user roles:

1. **CLIENT**: Clients can initiate and manage construction projects, track progress, interact with vendors and suppliers, and monitor budgets.

2. **VENDOR**: Vendors can register on the platform, receive project assignments from clients, update project status, and communicate with clients.

3. **SUPPLIER**: Suppliers are part of the ecosystem and provide access to construction materials and supplies for ongoing projects.

4. **ADMIN**: Admin role has access to the administrative functions of the application.

## Technology

The project is developed using the following technologies:

- Java 17
- Spring Boot
- Spring Data JPA
- WebSocket (using STOMP protocol)
- Oracle Database
- Lombok

## Features

NextStep offers the following key features:

- Real-Time Messaging and Notification (using STOMP protocol)
- Email Verification
- Finding and Assigning Vendors
- Project Status & Progress Tracking
- Finding Material & Supplies
- Rating & Review System
- Budget Tracking
- Construction Plan Generation

## Setup

To set up the database, follow these steps:

1. Set the database configuration in the `application.properties` file located in the project's root directory.

2. Update the following properties with your Oracle database connection details:

```properties
spring.datasource.url=jdbc:oracle:thin:@//YOUR_HOST:1521/xepdb1
spring.datasource.username=NEXT_STEP
spring.datasource.password=next_step
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect
```
## Setup

Make sure you have Java 17 installed and compatible with the project's setup.

Build and run the application using your favorite Java IDE or build tools.

## API Documentation

Once the API is up and running, you can access the documentation and interact with the endpoints using Swagger UI.

To access the API documentation, open your web browser and go to http://localhost:8080/swagger-ui.html. This will display the API documentation with the available endpoints and their descriptions.

## Contributing

We welcome contributions to enhance NextStep and make it even more powerful.


## License

This project is licensed under the MIT License. Feel free to use, modify, and distribute it as per the terms of the license.

Best regards,  
KHALED JEETAN  
khalidgeetan@gmail.com  
Profile [here](https://github.com/khaledJeetan/) 

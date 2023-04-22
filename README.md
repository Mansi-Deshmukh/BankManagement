# BankManagement

This is a Java Spring Boot application for managing banks, branches, users, employees, accounts, loans, and loan payments. It provides a REST API for performing CRUD (Create, Read, Update, Delete) operations on the various entities in the system.

# Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- MySql
- Maven
- Hibernate

# Getting Started
To get started with the project, clone the repository to your local machine and import it into your favorite IDE. You will need to have Java 8 or higher installed on your machine.

To build and run the application, you can use the following Maven commands:
* mvn clean install
* mvn spring-boot:run

The application should be accessible at http://localhost:8080.

# REST API
The REST API provides the following endpoints for managing the entities in the system:

- /banks - CRUD operations for banks
- /branches - CRUD operations for branches
- /users - CRUD operations for users
- /employees - CRUD operations for employees
- /accounts - CRUD operations for accounts
- /loans - CRUD operations for loans
- /payments - CRUD operations for loan payments
Each endpoint supports the standard HTTP methods (GET, POST, PUT, DELETE) for performing the respective operations.

# Data Model
The data model consists of the following entities and their relationships:

Bank - a bank has one or more branches
Branch - a branch belongs to one bank and has one or more employees and users
User - a user belongs to one branch and has one or more accounts and loans
Employee - an employee belongs to one branch and can manage accounts and loans for users
Account - an account belongs to one user and can have one or more loans
Loan - a loan belongs to one user and can have one or more payments
Payment - a payment belongs to one loan

# Contributing
Contributions to the project are welcome! If you find any bugs or have any feature requests, please open an issue or submit a pull request.

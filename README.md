# **`Spring Boot Project`**

This project is a Spring Boot application that uses PostgreSQL as its database.

# #  Prerequisites
Before you begin, ensure you have met the following requirements:

You have installed Java (JDK 17).

You have installed Lombok.

You have installed PostgreSQL.

You have a PostgreSQL database created named books.
# #  Setting Up the Database
Start your PostgreSQL server.
Create a new database named "books" by running the following command:

CREATE DATABASE books;

# #  Database Configuration
The application uses the following database configuration:

Database Name: books

Username: postgres

Password: 123

These settings are configured in the application.properties file.

# # Running the Application
Build the application using Maven:

mvn spring-boot:run

# # Database Migrations
The application uses database migrations to set up the initial schema and seed data. Upon startup, the application will automatically apply the necessary migrations, which include creating an admin user with the following credentials:

Username: admin

Password: 100

# # Configuration
All project settings can be found in the application.properties file located in the src/main/resources directory. Adjust these settings as needed for your environment.

# # Notes
Ensure PostgreSQL is running and accessible with the provided username and password.

If you encounter any issues, check the configuration settings in application.properties.

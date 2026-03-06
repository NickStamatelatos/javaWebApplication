Java web application

This is a spring boot web application with JWT authentication and MongoDB integration. It has been created for practice purposes, and it simulates an authentication and authorization practice.

Features

JWT Authentication: Securing users with JSON Web Tokens!

. MongoDB Integration: A non-SQL database for flexible data storage and easy manipulation

Spring Security: Advanced security and management features of Spring Boot!

RESTful API: Clean REST endpoints for user registration and authentication so you will be able to manipulate or edit data easier.

. Docker Support: Containerized deployment with Docker Compose

. API Documentation: OpenAPI/Swagger UI integration to document the features easier

Technologies Used

JAVA 25, Spring Boot 4.0.1, Spring Security, Spring Data MongoDB, JWT 0.13.0 Lombok SpringDoc OpenAPI 2.6.0 Maven Docker

Getting started on the project:





Open cmd and copy the commands git clone https://github.com/NickStamatelatos/javaWebApplication

cd javaWebApplication



Configure MongoDB connection in src/main/resources/application.properties spring.data.mongodb.uri=mongodb://The URL you want to use/The name of your database



Configure JWT secret key and expiration token time from appplication.properties jwt.secret=replace your secret key here.

jwt.expiration=here replace the time you want the token to be active in ms



Build and run on the Windows terminal you had open earlier: mvnw. cmd clean install

mvnw.cmd spring-boot:run

Then open another terminal in the same folder.

docker build -t java-web-app docker run -p 8080:8080 java-web-app docker-compose up -d



For Swagger, you can go on http://localhost:8080/swagger-ui.html


Java web application

This is a spring boot web application with JWT authentication and MongoDB integration.
it has been created for practice perspires, and it's simulate an authentication and authorization practice

Features

. JWT Authentication: Securing users with JSON Web Tokens!

. MongoDB Integration: A nonSql database for flexible data storage and easy manipulation

. Spring Security: Advanced security and management features of spring boot!

. RESTful API: Clean REST endpoints for user registration and authentication so you will be able to manipulate or edit data easier

. Docker Support: Containerized deployment with Docker Compose

. API Documentation: OpenAPI/Swagger UI integration for document the feasters easier

Technologies Used

JAVA 25
Spring Boot 4.0.1
Spring Security
Spring Data MongoDB
JWT 0.13.0
Lombok
SpringDoc OpenAPI 2.6.0
Maven
Docker

Getting start the project:

1. Open cmd and copy the commands
    git clone https://github.com/NickStamatelatos/javaWebApplication

    cd javaWebApplication

2. Configure MongoDB connection in src/main/resources/application.properties
spring.data.mongodb.uri=mongodb://The url you want to use/The name of your database
3. Config JWT Secret key and expiration token time
    from appplication.properties
    jwt.secret=replace your secret key here

    jwt.expiration=here replace the time you want the token tto be active in ms
4. Build and Run
   On windows terminal you had open earlier
    mvnw.cmd clean install

    mvnw.cmd spring-boot:run

    then open another terminal on the same folder

    docker build -t java-web-app
    docker run -p 8080:8080 java-web-app
    docker-compose up -d
5. For swagger, you can go on http://localhost:8080/swagger-ui.html


    



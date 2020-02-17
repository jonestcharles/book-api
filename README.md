A Basic REST API built with Spring Boot. Serves content from a local H2 database.

To run:

Clean, package, and run tests with 'mvn clean package'

Run web application with 'mvn spring-boot:run'

To shutdown:

Enter 'curl -X POST localhost:8080/actuator/shutdown'

Based on 2 models, Book and Review. Each Review has one and only one Book; a Book may have many
Reviews.

The BookServiceImpl and ReviewServiceImpl classes contain support for basic CRUD operations on each
model. Each Model has a controller. BookController contains the endpoint mapping for CRUD operations
on Books, or a single Book by ID. ReviewController contains the same for reviews, currently only
GET and DELETE operations for a Review by ID are supported. Exceptions are handled by the
GlobalExceptionHandler class, which handles errors using the ApiError wrapper model for exceptions.


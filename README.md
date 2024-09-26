# User and Email Microservices

This repository contains two microservices: User and Email. The User service manages user creation and persistence, while the Mail service is responsible for sending email notifications and communications. Both services communicate using RabbitMQ.

## User Microservice

### Overview
The User service is responsible for managing the users of the application, including the registration and persistence of information in the database. Once the user is created, it integrates with the email sending service to notify users of the success of the registration.

### Major Classes
- UserService: Manages the business logic for user creation and persistence.
- UserProducer: Responsible for sending messages to the RabbitMQ queue. After the user is created, an EmailDTO object is created and sent.

### RabbitMQ Integration
After the user is created in the UserService, the user is sent to the UserProducer, where the EmailDTO object is created, serialized into JSON, and sent to the RabbitMQ queue.

## Email Microservice

### Overview
The Email service is responsible for sending notifications and email communications to users of the application. It receives messages through a RabbitMQ queue, processes those messages, and sends the emails based on the information contained in them.

### Major Classes
- EmailConsumer: Listens to the email queue and processes incoming messages.
- EmailService: Implements the email sending logic by receiving the EmailModel object from the EmailConsumer and sending it.

### RabbitMQ Integration
EmailConsumer listens to the mail queue and, upon receiving a message, copies the properties of the EmailRecordDto to a new EmailModel object. This object is then sent to the EmailService, where the email's sending logic is implemented.

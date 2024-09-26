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

The email service is responsible for sending notifications and email communications to users of the application. It receives messages through a RabbitMQ queue, processes those messages, and sends the emails according to the information contained in them.
The EmailConsumer class is responsible for listening to the email queue and processing incoming messages.
After receiving the message, the properties of the EmailRecordDto are copied to a new EmailModel object and sent to EmailService which is where the email sending logic is implemented.

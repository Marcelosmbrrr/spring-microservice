# User microservice

The User service is responsible for managing users in the application, including registration and persistence of information in the database. In addition, it integrates with the email sending service using RabbitMQ to notify users about the success of their registration.
In it you can find the UserProducer class, which is responsible for sending messages to the RabbitMQ queue.
After the user is created in the UserService, it is sent to the UserProducer, where the EmailDTO object is created, serialized into JSON and sent to the RabbitMQ queue.

# Email microservice

The email service is responsible for sending notifications and email communications to users of the application. It receives messages through a RabbitMQ queue, processes those messages, and sends the emails according to the information contained in them.
The EmailConsumer class is responsible for listening to the email queue and processing incoming messages.
After receiving the message, the properties of the EmailRecordDto are copied to a new EmailModel object and sent to EmailService which is where the email sending logic is implemented.

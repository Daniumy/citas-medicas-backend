# citas-medicas-backend
Simple backend made with Spring that handles a basic medical appointments app following a class diagram.

Used Spring Security to ask for a login before being able to use any of the rest APIs, once your user is validated (it is found in the database, which then checks your password that has been encrypted) he receives a JWT token that he can use to authenticate his API calls.

The database is a SQL one created with MySQL, there is also a very simple queue messaging with RabbitMQ that takes care of publishing every "diagnostico" added to the SQL database and then the MongoDB NoSQL database consumes such object and also adds it to that database, so that read operations of "diagnosticos" are made using the NoSQL database and write operations are made to the SQL one.

![diagram-image](https://github.com/Daniumy/citas-medicas-backend/blob/main/diagram.jpg)
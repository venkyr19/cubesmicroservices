# Venky Cubes
Venky Cubes is a RESTful web service to manage (add, delete) and retrieve cube objects. It allows users to add, delete and get cubes.

# Table of Contents
**[Tools and Libraries](#tools-and-libraries)**<br>
**[APIs](#apis)**<br>
**[Set up](#set-up)**<br>
**[Usage](#usage)**<br>

## Tools and Libraries
<table>
    <tr>
        <td>Spring Boot</td>
        <td>Spring Framework</td>
    </tr>
    <tr>
        <td>Spring Web</td>
        <td>Building RESTful web applications</td>
    </tr>
    <tr>
        <td>Spring Data JPA</td>
        <td>Interacting with the database</td>
    </tr>
    <tr>
        <td>Lombok</td>
        <td>Reducing boilerplate code</td>
    </tr>
    <tr>
        <td>JUnit 5 and Mockito</td>
        <td>Testing</td>
    </tr>
    <tr>
        <td>Gradle</td>
        <td>Build tool</td>
    </tr>
    <tr>
        <td>MySQL</td>
        <td>The database used for persisting the application's data</td>
    </tr>
    <tr>
        <td>IDE</td>
        <td>IntelliJ</td>
    </tr>
</table>

## APIs
### Add a cube

Endpoint: POST /cube

Description: Adds a cube to the system.

Request Body:
```
{
    "volume": 125.0
}
```
Response Body:
```
{
    "id": "0c7d2f69-1d48-4e0f-bc7f-8c6130e7eb61",
    "x": 5.0,
    "y": 5.0,
    "z": 5.0,
    "volume": 125.0,
    "createdTime": "2022-03-01T15:14:55.520496"
}
```
### Get a cube using id </h3>

Endpoint: GET /cube?id={id}

Description: Retrieves a cube by its id (uuid).

Response Body:
```
{
    "id": "0c7d2f69-1d48-4e0f-bc7f-8c6130e7eb61",
    "x": 5.0,
    "y": 5.0,
    "z": 5.0,
    "volume": 125.0,
    "createdTime": "2022-03-01T15:14:55.520496"
}
```

### Get a cube using volume

Endpoint: GET /cube?volume={volume}

Description: Retrieves a cube by its volume.

Response Body:
```
{
    "id": "0c7d2f69-1d48-4e0f-bc7f-8c6130e7eb61",
    "x": 5.0,
    "y": 5.0,
    "z": 5.0,
    "volume": 125.0,
    "createdTime": "2022-03-01T15:14:55.520496"
}
```

## Set up

1. Install <a href="https://dev.mysql.com/downloads/mysql/">MySQL</a>
2. Create a database named cubes_db
```
# Connect to MySQL using your MySQL password and username
# The username and password are set in application.yml located under src/main/resources
> mysql -u root

# Create database named cubes_db
> create database cubes_db
```
3. Clone this project to your local directory
4. Import it into IntelliJ
    1. Ensure you have the Lombok Plugin installed
    2. Enabled AnnotationsProcessing in Settings

## Usage

### Running Application

1. Start the application
```
./gradlew bootRun
```

2. Invoke APIs
```
# Create a cube
curl --location --request POST 'http://localhost:8081/cube' \
--header 'Content-Type: application/json' \
--data-raw '{
    "volume": 64.0
    }'
    
# Get cube by id
curl --location --request GET 'http://localhost:8081/cube?id=79df4549-216d-4010-9bff-7c37767eeb4c'

# Get cube by volume
curl --location --request GET 'http://localhost:8081/cube?volume=8.0'
```

3. Stop the application
```
Control + C
```

### Running Tests

```
./gradlew tests
```

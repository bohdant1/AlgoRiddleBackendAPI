# AlgoRiddle Backend API

Welcome to AlgoRiddle! This repository contains the backend API for AlgoRiddle, a platform for practicing algorithmic challenges inspired by LeetCode. This backend is built using Spring Boot, Gradle, Spring Security with Firebase Authentication, and utilizes testing with JUnit and Mockito. It's dockerized for easy deployment using Docker and Docker Compose.

## Prerequisites

Before running the application, ensure you have the following prerequisites installed:

- Java Development Kit (JDK) 17 or higher
- Docker
- Docker Compose

## Getting Started

Follow these steps to get the backend API up and running:

### 1. Clone the Repository

`git clone https://github.com/tymob0/AlgoRiddleBackendAPI`

### 2. Configure Firebase Admin SDK

- Create a Firebase project via the [Firebase Console](https://console.firebase.google.com/).
- Obtain a new private key for the Firebase Admin SDK (json file)
- Stringify the JSON 
- Pass it as ENV VAR ${FIREBASE_CONFIG_JSON}
- Configure Firebase Authentication in your Firebase project to allow authentication via email and password.

### 4. Build the Application

- `cd AlgoRiddle`
- `./gradlew build`

### 5. Run the Application with Docker Compose

This command will build the Docker images for the application and its dependencies and start the containers.

`docker compose up`

### 6. Access the API Documentation

<img src="https://i.imgur.com/ri0ZBrG.png" class="center"/>

Once the application is running, you can access Swagger at `http://localhost:8080/swagger-ui/index.html`.

The OpenAPI Specification provides a formal standard for describing HTTP APIs.This allows people to understand how an API works, generate client code, create tests, apply design standards, and much, much more.

Make use of embedded API security by clicking on "Authorize" passing a valid JWT token.

### 7. Testing

To run the tests, execute the following command:

`./gradlew test`

This will run both unit tests and integration tests using JUnit and Mockito.

### 8. Security

#### 8.1 Authentication
- **Firebase Authentication**: Utilized for authentication, providing a secure and scalable approach to authenticate users.

- **Angular Front-end Integration**: The Angular front-end application integrates Firebase's JavaScript Client Library to facilitate login and registration mechanisms.

- **JWT Attachment**: Upon successful authentication, the Angular front-end app attaches a JSON Web Token (JWT) received from Firebase Authentication Server.

- **JWT Verification**: This JWT is then verified against Firebase Authentication Server, ensuring access to protected endpoints on the back-end.


#### 8.2 Authorization 
The provided code implements custom authorization logic to control access to resources based on user roles and permissions. It defines a set of roles, each with associated permissions.

##### Role Definition

Roles are defined as an enum called `Role`, which contains two roles: `USER` and `ADMIN`. Each role has a set of associated permissions.

##### Permissions

Permissions are defined as an enum called `Permission`. Each permission represents a specific action that a user with a certain role can perform. For example, `USER_READ`, `USER_UPDATE`, `USER_DELETE`, and `USER_CREATE` represent actions related to user data, while `ADMIN_READ`, `ADMIN_UPDATE`, `ADMIN_DELETE`, and `ADMIN_CREATE` represent actions related to administrative tasks.

##### Role Permissions

- **USER Role**: The `USER` role has permissions to read, update, delete, and create user data (`USER_READ`, `USER_UPDATE`, `USER_DELETE`, `USER_CREATE`).

- **ADMIN Role**: The `ADMIN` role inherits all permissions of the `USER` role and additionally has permissions for administrative tasks (`ADMIN_READ`, `ADMIN_UPDATE`, `ADMIN_DELETE`, `ADMIN_CREATE`).

## 9. Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.








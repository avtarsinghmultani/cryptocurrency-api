# cryptocurrency-api
This is a Back-End application for Cryptocurrency Analysis project using Spring Boot, h2 database & jUnit for RESTful APIs. 
<br />
The Fron-End application can be found at [https://github.com/rmit-s3530196-avtar-singh/cryptocurrency-react-redux](https://github.com/rmit-s3530196-avtar-singh/cryptocurrency-react-redux) build with React/Redux.<br />
### Note: This application uses h2 database build into spring boot. The database gets seeded at startup from ```data.sql``` .

## Overview of Stack
- Server
  - Spring Boot
  - Hibernate & JPA
  - REST based API
  - h2 for data storage
  - jUnit for testing
- Client
  - React 16
  - Redux 4
  - Material UI & Bootstrap
  - axios for REST API requests

## Scripts
In the project directory, you can run:
### ``` mvn install ```
When first cloning the repo or adding new dependencies, run this command. This will:
- Install dependencies from pom.xml
### ``` mvn spring-boot:run ```
To start the app, run this command. This will:
- start the web server on https://localhost:8080
- navigate to http://localhost:8080/currency/all to view all currencies and http://localhost:8080/currency/views for Currency Analysis.

## Setup
### Build and run your code in Visual Studio
1. To build your project, choose Build Solution from the Build menu. The Output window shows the results of the build process.
2. To run the code, on the menu bar, choose Debug, Start without debugging.
3. The application will create databases and seed them at start.
4. Open browser and navigate to http://localhost:8080/currency/all for currencies and http://localhost:8080/currency/views for Currency Analysis.

This template was developed and tested on MacOS using Visual Studio 2019.


# Java CRUD with Repository Pattern

This project is a CRUD (Create, Read, Update, Delete) application using Java with the Repository pattern implementation. It supports both in-memory storage and database persistence.

## Architecture

- **Repository Pattern**: Clean separation between data access and business logic
- **Dependency Injection**: Easy switching between storage implementations
- **Clean Code**: Modular and maintainable structure

## Storage Options

### In-Memory Storage
- Data stored in HashMap during runtime
- No external dependencies required
- Data lost when application closes

### Database Storage  
- MariaDB database persistence
- JDBC connection
- Data persists between sessions

## Requirements

### For Memory Storage
- Java JDK installed

### For Database Storage
- Java JDK installed
- MariaDB database
- JDBC driver for MariaDB

## Features

- Add new Students
- Search Students by name
- Update Student names
- Delete Students
- Show all Students
- Choose between memory or database storage

## How to Switch Storage

In `Ui.java` main method, change the repository implementation:
```java
// For memory storage
StudentRepository repo = new StudentMemLocalDataSource();

// For database storage  
StudentRepository repo = new StudentApiRemoteDataSource();

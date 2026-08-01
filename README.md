# Spring Boot BDD with Cucumber

Proof of concept demonstrating behavior-driven development (BDD) with Cucumber, Spring Boot, JPA, and MySQL. The project expresses user-persistence rules in Gherkin and connects those scenarios to Spring-managed services and a real database.

## What this project demonstrates

- Executable business scenarios written in Gherkin
- Cucumber step definitions backed by Spring Boot
- Persistence through Spring Data JPA and MySQL
- Validation of successful user registration
- Prevention of duplicate email registration
- Test-environment configuration with automatic schema lifecycle

## Scenarios

The feature suite currently documents two behaviors:

```gherkin
Feature: the user can be persisted on the database

Scenario: User created successfully
  When valid user data is provided
  Then the user is persisted
  And the user can be retrieved by id

Scenario: Prevent duplicate email registration
  Given an existing user email
  When another user is persisted with the same email
  Then an EmailAlreadyExistsException is raised
```

The executable specification is available at [`src/test/resources/features/stepDefinition.feature`](src/test/resources/features/stepDefinition.feature).

## Technology

- Java 21
- Spring Boot 3.3.2
- Spring Data JPA
- Cucumber 7.14
- JUnit 5 and JUnit Platform Suite
- MySQL 8
- Maven Wrapper
- Docker Compose

## Project structure

```text
src
|-- main
|   |-- java/.../entities            # User persistence model
|   |-- java/.../repositories        # Spring Data repositories
|   |-- java/.../services            # Business rules
|   `-- java/.../resources           # HTTP controller
`-- test
    |-- java/.../config              # Cucumber and Spring configuration
    |-- java/.../step                # Gherkin step definitions
    `-- resources/features           # Executable specifications
```

## Prerequisites

- Java 21
- Docker with Docker Compose

The Maven Wrapper is included, so a global Maven installation is optional.

## Running locally

Start MySQL:

```bash
docker compose up -d db
```

Run the application on Linux or macOS:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

The application uses the following local database configuration:

| Setting | Value |
| --- | --- |
| Host | `localhost:3306` |
| Database | `cucumber` |
| Username | `cucumber` |
| Password | `cucumber` |

These credentials are intentionally local and must not be reused in a shared or production environment.

## Running the tests

With MySQL running, execute the current Maven test lifecycle:

```bash
./mvnw test
```

On Windows:

```powershell
.\mvnw.cmd test
```

The current Maven configuration validates the Spring application context. The Gherkin feature, Spring configuration, and step definitions are present, but automatic scenario discovery still needs a compatible Cucumber JUnit Platform engine (or a JUnit 4 runner) wired into the build. Until that is added, the repository should not claim the feature scenarios as part of the passing default lifecycle.

The test profile is configured with Hibernate `create-drop` behavior for test data isolation.

Stop the database after use:

```bash
docker compose down
```

Remove its local volume as well:

```bash
docker compose down -v
```

## Business rule under test

The service checks whether an email already exists before persisting a user. A duplicate registration raises `EmailAlreadyExistsException`, and the Cucumber scenario asserts both the exception type and message.

This keeps the feature focused on observable business behavior instead of implementation details.

## Scope and next steps

This is a focused testing lab rather than a production API. Useful extensions include:

- Exercise the behavior through HTTP instead of calling the service directly.
- Use Testcontainers to isolate MySQL automatically during tests.
- Wire the Cucumber engine into Maven and generate HTML reports.
- Add scenarios for invalid input, concurrent registrations, and missing users.
- Separate unit, integration, and acceptance-test stages.
- Add a GitHub Actions workflow that runs the full suite on every change.

# Spring Boot 2.7 to 3.x Modernization Lab

This module demonstrates migration from a Spring Boot 2.7 application to Spring Boot 3.x.

## Before Migration

The baseline was a Spring Boot 2.7.18 Java 17 application with:

- Spring Web
- Spring Data JPA
- H2
- Bean Validation
- Spring Boot Test
- `javax.persistence` imports
- `javax.validation` imports
- A JUnit 4 style Spring test

The app exposes a small `User` API:

- `GET /users`
- `GET /users/{id}`
- `POST /users`
- `DELETE /users/{id}`

## After Migration

The current module has been migrated to Spring Boot 3.5.x. The source now uses:

- `jakarta.persistence`
- `jakarta.validation`
- JUnit Jupiter style tests
- Spring Boot 3 managed dependencies

## Automated By OpenRewrite

The V1 recipe in `rewrite.yml` handles the main framework migration:

- Upgraded Spring Boot to 3.5.x
- Migrated Java EE `javax.*` imports to Jakarta `jakarta.*`
- Updated compatible Spring and test APIs
- Removed unused imports and applied formatting

## Manual Follow-Up

Review these manually after automation:

- HTTP behavior and error response compatibility
- Persistence behavior after Hibernate upgrades
- Validation messages and constraint behavior
- Test coverage for controller edge cases
- Any production-specific configuration that was not represented in this lab

## Run

```bash
mvn -pl apps/boot-27-to-3x spring-boot:run
```

## Test

```bash
mvn -pl apps/boot-27-to-3x test
```

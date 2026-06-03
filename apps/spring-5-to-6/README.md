# Spring Framework 5 to 6 Modernization Lab

This module demonstrates migration of a plain Spring Framework application from Spring 5.3.x to Spring 6.x.

It intentionally does not use Spring Boot.

## Before Migration

The baseline was a Java 17 Maven module using Spring Framework 5.3.x directly, with explicit dependencies on:

- `spring-context`
- `spring-beans`
- `spring-core`
- `spring-test`

The app contains a simple customer domain:

- `Customer`
- `CustomerRepository`
- `InMemoryCustomerRepository`
- `CustomerService`
- `AppConfig`

It also includes `application-context.xml` as a small XML configuration example and a Spring context test.

## After Migration

The current module has been migrated to Spring Framework 6.2.x while remaining a non-Boot Spring application.

The app still uses:

- Java 17
- Explicit Spring Framework dependencies
- Annotation-based component scanning
- A simple XML context example
- Spring Test with JUnit 4

## Automated By OpenRewrite

The V3 recipe in `rewrite.yml` handles:

- Spring Framework dependency upgrades to 6.2.x
- Spring API compatibility changes where applicable
- Import cleanup and formatting

This module is intentionally small, so the automated diff is mostly dependency and compatibility focused.

## Manual Follow-Up

Review these manually after automation:

- XML configuration compatibility in larger applications
- Bean lifecycle and proxy behavior
- Removed or deprecated Spring APIs not represented in the sample
- Whether to migrate tests from JUnit 4 to JUnit Jupiter
- Whether to introduce dependency management for larger multi-module Spring projects

## Test

```bash
mvn -pl apps/spring-5-to-6 test
```

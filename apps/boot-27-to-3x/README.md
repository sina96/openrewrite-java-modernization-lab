# Boot 2.7 to 3.x Modernization Lab

This module is the legacy baseline for an OpenRewrite migration exercise.

It intentionally starts on Spring Boot 2.7.18 with Java 17 and uses the pre-Spring Boot 3 namespace imports:

- `javax.persistence` for JPA entities
- `javax.validation` for validation annotations

Do not apply the Spring Boot 3 migration to this module yet. The point of this version is to provide a realistic before-state that OpenRewrite recipes can modernize later.

## Run

```bash
mvn -pl apps/boot-27-to-3x spring-boot:run
```

## Test

```bash
mvn -pl apps/boot-27-to-3x test
```

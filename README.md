# OpenRewrite Java Modernization Lab

This repository is a hands-on lab for practicing Java and Spring modernization with OpenRewrite. It contains small, focused Maven modules that start from common legacy baselines and show how automated recipes can move the codebase forward.

The lab is intentionally split into independent apps so each migration can be understood without unrelated framework noise.

## Modules

| Module | Purpose | Current State |
| --- | --- | --- |
| `apps/boot-27-to-3x` | Spring Boot 2.7 to Spring Boot 3.x migration | Migrated to Spring Boot 3.5.x with Jakarta imports |
| `apps/java-8-to-17` | Plain Java 8 to Java 17 migration | Migrated to Java 17, JUnit Jupiter, and `java.time` cleanup |
| `apps/spring-5-to-6` | Plain Spring Framework 5 to 6 migration | Migrated to Spring Framework 6.2.x while still not using Spring Boot |

Recipes are defined in [rewrite.yml](rewrite.yml). For a practical explanation of recipes, dry runs, plugin wiring, and how to read Rewrite output, see [docs/openrewrite-basics.md](docs/openrewrite-basics.md).

Official OpenRewrite docs:

- [Running recipes](https://docs.openrewrite.org/running-recipes)
- [Maven plugin reference](https://docs.openrewrite.org/reference/rewrite-maven-plugin)
- [Recipe catalog](https://docs.openrewrite.org/recipes)

## Run Tests

Run one module:

```bash
mvn -pl apps/boot-27-to-3x test
mvn -pl apps/java-8-to-17 test
mvn -pl apps/spring-5-to-6 test
```

Run the full reactor:

```bash
mvn test
```

## OpenRewrite Workflow

Use dry runs before applying recipes:

```bash
mvn -pl apps/java-8-to-17 rewrite:dryRun
```

Apply recipes only after reviewing the generated patch:

```bash
mvn -pl apps/java-8-to-17 rewrite:run
```

Each app README describes what was automated and what still belongs in manual review.

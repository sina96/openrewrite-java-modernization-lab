# Java 8 to 17 Modernization Lab

This module demonstrates migration of a plain Java Maven project from Java 8 style code to Java 17.

It does not use Spring or Spring Boot.

## Before Migration

The baseline was a Java 8 Maven module with:

- `maven.compiler.source` and `maven.compiler.target` set to `8`
- JUnit 4 tests
- A small order-processing domain
- Legacy date/time APIs: `Date`, `Calendar`, and `SimpleDateFormat`
- Loop-based collection processing
- String concatenation cleanup opportunities

The domain includes:

- `CustomerOrder`
- `OrderLine`
- `OrderProcessor`
- `OrderStatus`
- `Receipt`

## After Migration

The current module has been migrated to Java 17. It now uses:

- Maven compiler source/target `17`
- JUnit Jupiter
- `java.time.LocalDate`
- `Clock` injection for deterministic date tests
- Streams where they improve readability
- Parameterized tests
- Clearer test names with `@DisplayName`

## Automated By OpenRewrite

The V2 recipe in `rewrite.yml` handles:

- Java build version update to 17
- JUnit 4 to JUnit Jupiter migration
- JUnit dependency replacement
- Import cleanup and formatting

The Java 17 recipe did not automatically replace all legacy date/time APIs, because that choice depends on domain semantics.

## Manual Follow-Up

These changes were handled manually after the automated migration:

- Replaced `Date`, `Calendar`, and `SimpleDateFormat` with `java.time`
- Chose `LocalDate` as the domain date type
- Added `Clock` to make current-date behavior testable
- Replaced simple loops with streams where readability improved
- Improved test names
- Added parameterized tests

Further review candidates:

- Decide whether `LocalDate`, `Instant`, or `OffsetDateTime` is best for real production APIs
- Consider making domain objects immutable records
- Add edge-case tests for invalid order lines

## Test

```bash
mvn -pl apps/java-8-to-17 test
```

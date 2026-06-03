# V3 Migration Notes: Spring Framework 5 to 6

## Baseline

The `apps/spring-5-to-6` module is a plain Maven project using Spring Framework 5.3.x on Java 17.

It intentionally does not use Spring Boot. Dependencies are declared directly for `spring-context`, `spring-beans`, `spring-core`, and `spring-test`.

## OpenRewrite Dry Run

Start with a dry run so the Spring 6 migration diff can be reviewed before files are changed:

```bash
mvn -pl apps/spring-5-to-6 rewrite:dryRun
```

Review the generated patch and confirm the selected recipes are scoped to the Spring Framework 5 to 6 migration.

## Automated Migration

After reviewing the dry run, apply the selected recipes:

```bash
mvn -pl apps/spring-5-to-6 rewrite:run
```

Run tests immediately afterward:

```bash
mvn -pl apps/spring-5-to-6 test
```

## Manual Follow-Up

After automated migration, review areas that usually need human judgment:

- Direct Spring Framework dependency versions
- XML configuration compatibility
- Reflection or proxy behavior in older Spring applications
- Test infrastructure changes if moving from JUnit 4 to JUnit Jupiter

## Lessons Learned

Keep the Spring Framework baseline separate from the Spring Boot sample. A plain Spring app makes it easier to see which changes come from the framework migration itself rather than Boot-managed dependency and plugin upgrades.

# V2 Migration Notes: Java 8 to 17

## Baseline

The `apps/java-8-to-17` module is a plain Java Maven project with Java source and target set to 8.

It intentionally uses legacy Java patterns that are common in older services:

- `Date`, `Calendar`, and `SimpleDateFormat`
- JUnit 4 tests
- Mutable domain objects
- Loop-based collection processing
- String concatenation that could be simplified

No Java 17 migration has been applied yet.

## OpenRewrite Dry Run

Use a dry run first to inspect the proposed changes before editing files:

```bash
mvn -pl apps/java-8-to-17 rewrite:dryRun
```

Review the generated patch output and confirm the recipes match the intended Java 8 to 17 migration path.

## Automated Migration

After reviewing the dry run, apply the selected OpenRewrite recipes:

```bash
mvn -pl apps/java-8-to-17 rewrite:run
```

Run tests immediately afterward:

```bash
mvn -pl apps/java-8-to-17 test
```

## Manual Follow-Up

After the automated migration, review changes that usually need human judgment:

- Date/time behavior when replacing `Date`, `Calendar`, or `SimpleDateFormat`
- Public API compatibility for mutable domain objects
- Test style and assertion clarity after JUnit migration
- Cleanup opportunities that recipes intentionally leave conservative

## Lessons Learned

Keep the baseline and migration commits separate. The baseline should compile and test cleanly before any recipe runs, so the migration diff is easy to review and failures are easier to isolate.

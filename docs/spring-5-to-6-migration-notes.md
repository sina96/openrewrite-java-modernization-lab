# V3 Migration Notes: Spring Framework 5 to 6

## Baseline

The `apps/spring-5-to-6` module is a plain Maven project using Spring Framework 5.3.x on Java 17.

It intentionally does not use Spring Boot. Dependencies are declared directly for `spring-context`, `spring-beans`, `spring-core`, and `spring-test`.

## OpenRewrite Dry Run

Start with a dry run so the Spring 6 migration diff can be reviewed before files are changed:

```bash
mvn -pl apps/spring-5-to-6 rewrite:dryRun
```

LOGS:

```
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------< com.example.openrewrite:spring-5-to-6 >----------------
[INFO] Building Spring Framework 5 to 6 Modernization Lab 0.1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> rewrite:6.40.0:dryRun (default-cli) > process-test-classes @ spring-5-to-6 >>>
[INFO]
[INFO] --- resources:3.4.0:resources (default-resources) @ spring-5-to-6 ---
[INFO] Copying 1 resource from src/main/resources to target/classes
[INFO]
[INFO] --- compiler:3.15.0:compile (default-compile) @ spring-5-to-6 ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.4.0:testResources (default-testResources) @ spring-5-to-6 ---
[INFO] skip non existing resourceDirectory /Users/sinabastani/Desktop/projects/Java/openrewrite-modernization-lab/apps/spring-5-to-6/src/test/resources
[INFO]
[INFO] --- compiler:3.15.0:testCompile (default-testCompile) @ spring-5-to-6 ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] <<< rewrite:6.40.0:dryRun (default-cli) < process-test-classes @ spring-5-to-6 <<<
[INFO]
[INFO]
[INFO] --- rewrite:6.40.0:dryRun (default-cli) @ spring-5-to-6 ---
[INFO] Using active recipe(s) [com.example.openrewrite.V3SpringFramework6Migration]
[INFO] Using active styles(s) []
[INFO] Validating active recipes...
[INFO] Project [Spring Framework 5 to 6 Modernization Lab] Resolving Poms...
[INFO] Project [Spring Framework 5 to 6 Modernization Lab] Parsing source files
[INFO] Running recipe(s)...
[WARNING] These recipes would make changes to apps/spring-5-to-6/pom.xml:
[WARNING]     org.openrewrite.java.spring.framework.UpgradeSpringFramework_6_2
[WARNING]         org.openrewrite.java.spring.framework.UpgradeSpringFramework_6_1
[WARNING]             org.openrewrite.java.spring.framework.UpgradeSpringFramework_6_0
[WARNING]                 org.openrewrite.java.dependencies.UpgradeDependencyVersion
[WARNING]             org.openrewrite.java.dependencies.UpgradeDependencyVersion
[WARNING]         org.openrewrite.java.dependencies.UpgradeDependencyVersion: {groupId=org.springframework, artifactId=*, newVersion=6.2.x}
[WARNING] Patch file available:
[WARNING]     /Users/sinabastani/Desktop/projects/Java/openrewrite-modernization-lab/apps/spring-5-to-6/target/rewrite/rewrite.patch
[WARNING] Estimate time saved: 15m
[WARNING] Run 'mvn rewrite:run' to apply the recipes.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  9.530 s
[INFO] Finished at: 2026-06-03T23:30:42+02:00
[INFO] ------------------------------------------------------------------------
```

Review the generated patch and confirm the selected recipes are scoped to the Spring Framework 5 to 6 migration.

## Automated Migration

After reviewing the dry run, apply the selected recipes:

```bash
mvn -pl apps/spring-5-to-6 rewrite:run
```

logs:

```
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------< com.example.openrewrite:spring-5-to-6 >----------------
[INFO] Building Spring Framework 5 to 6 Modernization Lab 0.1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> rewrite:6.40.0:run (default-cli) > process-test-classes @ spring-5-to-6 >>>
[INFO]
[INFO] --- resources:3.4.0:resources (default-resources) @ spring-5-to-6 ---
[INFO] Copying 1 resource from src/main/resources to target/classes
[INFO]
[INFO] --- compiler:3.15.0:compile (default-compile) @ spring-5-to-6 ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.4.0:testResources (default-testResources) @ spring-5-to-6 ---
[INFO] skip non existing resourceDirectory /Users/sinabastani/Desktop/projects/Java/openrewrite-modernization-lab/apps/spring-5-to-6/src/test/resources
[INFO]
[INFO] --- compiler:3.15.0:testCompile (default-testCompile) @ spring-5-to-6 ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] <<< rewrite:6.40.0:run (default-cli) < process-test-classes @ spring-5-to-6 <<<
[INFO]
[INFO]
[INFO] --- rewrite:6.40.0:run (default-cli) @ spring-5-to-6 ---
[INFO] Using active recipe(s) [com.example.openrewrite.V3SpringFramework6Migration]
[INFO] Using active styles(s) []
[INFO] Validating active recipes...
[INFO] Project [Spring Framework 5 to 6 Modernization Lab] Resolving Poms...
[INFO] Project [Spring Framework 5 to 6 Modernization Lab] Parsing source files
[INFO] Running recipe(s)...
[WARNING] Changes have been made to apps/spring-5-to-6/pom.xml by:
[WARNING]     org.openrewrite.java.spring.framework.UpgradeSpringFramework_6_2
[WARNING]         org.openrewrite.java.spring.framework.UpgradeSpringFramework_6_1
[WARNING]             org.openrewrite.java.spring.framework.UpgradeSpringFramework_6_0
[WARNING]                 org.openrewrite.java.dependencies.UpgradeDependencyVersion
[WARNING]             org.openrewrite.java.dependencies.UpgradeDependencyVersion
[WARNING]         org.openrewrite.java.dependencies.UpgradeDependencyVersion: {groupId=org.springframework, artifactId=*, newVersion=6.2.x}
[WARNING] Please review and commit the results.
[WARNING] Estimate time saved: 15m
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  8.511 s
[INFO] Finished at: 2026-06-03T23:31:33+02:00
[INFO] ------------------------------------------------------------------------
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

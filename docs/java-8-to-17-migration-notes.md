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
we got
```
[INFO] Scanning for projects...
[INFO]
[INFO] ----------------< com.example.openrewrite:java-8-to-17 >----------------
[INFO] Building Java 8 to 17 Modernization Lab 0.1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> rewrite:6.40.0:dryRun (default-cli) > process-test-classes @ java-8-to-17 >>>
[INFO]
[INFO] --- resources:3.4.0:resources (default-resources) @ java-8-to-17 ---
[INFO] skip non existing resourceDirectory /Users/sinabastani/Desktop/projects/Java/openrewrite-modernization-lab/apps/java-8-to-17/src/main/resources
[INFO]
[INFO] --- compiler:3.15.0:compile (default-compile) @ java-8-to-17 ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.4.0:testResources (default-testResources) @ java-8-to-17 ---
[INFO] skip non existing resourceDirectory /Users/sinabastani/Desktop/projects/Java/openrewrite-modernization-lab/apps/java-8-to-17/src/test/resources
[INFO]
[INFO] --- compiler:3.15.0:testCompile (default-testCompile) @ java-8-to-17 ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] <<< rewrite:6.40.0:dryRun (default-cli) < process-test-classes @ java-8-to-17 <<<
[INFO]
[INFO]
[INFO] --- rewrite:6.40.0:dryRun (default-cli) @ java-8-to-17 ---
[INFO] Using active recipe(s) [com.example.openrewrite.V2Java17Migration]
[INFO] Using active styles(s) []
[INFO] Validating active recipes...
[INFO] Project [Java 8 to 17 Modernization Lab] Resolving Poms...
[INFO] Project [Java 8 to 17 Modernization Lab] Parsing source files
[INFO] Running recipe(s)...
[INFO] Applying recipes would make no changes. No patch file generated.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  7.099 s
[INFO] Finished at: 2026-06-03T23:06:41+02:00
[INFO] ------------------------------------------------------------------------
```

Review the generated patch output and confirm the recipes match the intended Java 8 to 17 migration path.

the dry run showed that no changes were needed which means that we needed to update the recipes to match the intended Java 8 to 17 migration path.


Changes:

  - rewrite.yml now adds:
      - maven.compiler.source -> 17
      - maven.compiler.target -> 17
      - org.openrewrite.java.testing.junit5.JUnit4to5Migration

  - apps/java-8-to-17/pom.xml now includes the rewrite-testing-frameworks recipe dependency so the JUnit migration recipe is available.

after the change, the dry run showed that no changes were needed which means that we needed to update the recipes to match the intended Java 8 to 17 migration path.

logs:

```
[INFO] Scanning for projects...
[INFO]
[INFO] ----------------< com.example.openrewrite:java-8-to-17 >----------------
[INFO] Building Java 8 to 17 Modernization Lab 0.1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> rewrite:6.40.0:dryRun (default-cli) > process-test-classes @ java-8-to-17 >>>
[INFO]
[INFO] --- resources:3.4.0:resources (default-resources) @ java-8-to-17 ---
[INFO] skip non existing resourceDirectory /Users/sinabastani/Desktop/projects/Java/openrewrite-modernization-lab/apps/java-8-to-17/src/main/resources
[INFO]
[INFO] --- compiler:3.15.0:compile (default-compile) @ java-8-to-17 ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.4.0:testResources (default-testResources) @ java-8-to-17 ---
[INFO] skip non existing resourceDirectory /Users/sinabastani/Desktop/projects/Java/openrewrite-modernization-lab/apps/java-8-to-17/src/test/resources
[INFO]
[INFO] --- compiler:3.15.0:testCompile (default-testCompile) @ java-8-to-17 ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] <<< rewrite:6.40.0:dryRun (default-cli) < process-test-classes @ java-8-to-17 <<<
[INFO]
[INFO]
[INFO] --- rewrite:6.40.0:dryRun (default-cli) @ java-8-to-17 ---
[INFO] Using active recipe(s) [com.example.openrewrite.V2Java17Migration]
[INFO] Using active styles(s) []
[INFO] Validating active recipes...
[INFO] Project [Java 8 to 17 Modernization Lab] Resolving Poms...
[INFO] Project [Java 8 to 17 Modernization Lab] Parsing source files
[INFO] Running recipe(s)...
[WARNING] These recipes would make changes to apps/java-8-to-17/pom.xml:
[WARNING]     org.openrewrite.maven.ChangePropertyValue: {key=maven.compiler.source, newValue=17}
[WARNING]         org.openrewrite.maven.ChangePropertyValue: {key=maven.compiler.target, newValue=17}
[WARNING]             org.openrewrite.java.testing.junit5.JUnit4to5Migration
[WARNING]                 org.openrewrite.java.dependencies.RemoveDependency: {groupId=junit, artifactId=junit}
[WARNING]                 org.openrewrite.java.testing.junit5.AddJupiterDependencies
[WARNING] These recipes would make changes to apps/java-8-to-17/src/test/java/com/example/openrewrite/java8/OrderProcessorTest.java:
[WARNING]     org.openrewrite.java.migrate.UpgradeToJava17
[WARNING]         org.openrewrite.java.migrate.Java8toJava11
[WARNING]             org.openrewrite.java.migrate.UpgradeBuildToJava11
[WARNING]                 org.openrewrite.java.migrate.UpgradeJavaVersion
[WARNING]         org.openrewrite.java.migrate.UpgradeBuildToJava17
[WARNING]             org.openrewrite.java.migrate.UpgradeJavaVersion
[WARNING]         org.openrewrite.java.testing.junit5.JUnit4to5Migration
[WARNING]             org.openrewrite.java.testing.junit5.AssertToAssertions
[WARNING]             org.openrewrite.java.testing.junit5.UpdateBeforeAfterAnnotations
[WARNING]             org.openrewrite.java.testing.junit5.UpdateTestAnnotation
[WARNING]             org.openrewrite.java.dependencies.RemoveDependency: {groupId=junit, artifactId=junit}
[WARNING] Patch file available:
[WARNING]     /Users/sinabastani/Desktop/projects/Java/openrewrite-modernization-lab/apps/java-8-to-17/target/rewrite/rewrite.patch
[WARNING] Estimate time saved: 40m
[WARNING] Run 'mvn rewrite:run' to apply the recipes.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  8.036 s
[INFO] Finished at: 2026-06-03T23:13:27+02:00
[INFO] ------------------------------------------------------------------------
```

## Automated Migration

After reviewing the dry run, apply the selected OpenRewrite recipes:

```bash
mvn -pl apps/java-8-to-17 rewrite:run
```

logs:

```
[INFO] Scanning for projects...
[INFO]
[INFO] ----------------< com.example.openrewrite:java-8-to-17 >----------------
[INFO] Building Java 8 to 17 Modernization Lab 0.1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> rewrite:6.40.0:run (default-cli) > process-test-classes @ java-8-to-17 >>>
[INFO]
[INFO] --- resources:3.4.0:resources (default-resources) @ java-8-to-17 ---
[INFO] skip non existing resourceDirectory /Users/sinabastani/Desktop/projects/Java/openrewrite-modernization-lab/apps/java-8-to-17/src/main/resources
[INFO]
[INFO] --- compiler:3.15.0:compile (default-compile) @ java-8-to-17 ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.4.0:testResources (default-testResources) @ java-8-to-17 ---
[INFO] skip non existing resourceDirectory /Users/sinabastani/Desktop/projects/Java/openrewrite-modernization-lab/apps/java-8-to-17/src/test/resources
[INFO]
[INFO] --- compiler:3.15.0:testCompile (default-testCompile) @ java-8-to-17 ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] <<< rewrite:6.40.0:run (default-cli) < process-test-classes @ java-8-to-17 <<<
[INFO]
[INFO]
[INFO] --- rewrite:6.40.0:run (default-cli) @ java-8-to-17 ---
[INFO] Using active recipe(s) [com.example.openrewrite.V2Java17Migration]
[INFO] Using active styles(s) []
[INFO] Validating active recipes...
[INFO] Project [Java 8 to 17 Modernization Lab] Resolving Poms...
[INFO] Project [Java 8 to 17 Modernization Lab] Parsing source files
[INFO] Running recipe(s)...
[WARNING] Changes have been made to apps/java-8-to-17/pom.xml by:
[WARNING]     org.openrewrite.maven.ChangePropertyValue: {key=maven.compiler.source, newValue=17}
[WARNING]         org.openrewrite.maven.ChangePropertyValue: {key=maven.compiler.target, newValue=17}
[WARNING]             org.openrewrite.java.testing.junit5.JUnit4to5Migration
[WARNING]                 org.openrewrite.java.dependencies.RemoveDependency: {groupId=junit, artifactId=junit}
[WARNING]                 org.openrewrite.java.testing.junit5.AddJupiterDependencies
[WARNING] Changes have been made to apps/java-8-to-17/src/test/java/com/example/openrewrite/java8/OrderProcessorTest.java by:
[WARNING]     org.openrewrite.java.migrate.UpgradeToJava17
[WARNING]         org.openrewrite.java.migrate.Java8toJava11
[WARNING]             org.openrewrite.java.migrate.UpgradeBuildToJava11
[WARNING]                 org.openrewrite.java.migrate.UpgradeJavaVersion
[WARNING]         org.openrewrite.java.migrate.UpgradeBuildToJava17
[WARNING]             org.openrewrite.java.migrate.UpgradeJavaVersion
[WARNING]         org.openrewrite.java.testing.junit5.JUnit4to5Migration
[WARNING]             org.openrewrite.java.testing.junit5.AssertToAssertions
[WARNING]             org.openrewrite.java.testing.junit5.UpdateBeforeAfterAnnotations
[WARNING]             org.openrewrite.java.testing.junit5.UpdateTestAnnotation
[WARNING]             org.openrewrite.java.dependencies.RemoveDependency: {groupId=junit, artifactId=junit}
[WARNING] Please review and commit the results.
[WARNING] Estimate time saved: 40m
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  7.994 s
[INFO] Finished at: 2026-06-03T23:16:54+02:00
[INFO] ------------------------------------------------------------------------
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

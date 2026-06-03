From repo root:

```bash
mvn -pl apps/boot-27-to-3x rewrite:run
```

we get

```
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------< com.example.openrewrite:boot-27-to-3x >----------------
[INFO] Building Boot 2.7 to 3.x Modernization Lab 0.1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> rewrite:6.40.0:run (default-cli) > process-test-classes @ boot-27-to-3x >>>
[INFO]
[INFO] --- resources:3.2.0:resources (default-resources) @ boot-27-to-3x ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] Copying 1 resource
[INFO] Copying 0 resource
[INFO]
[INFO] --- compiler:3.10.1:compile (default-compile) @ boot-27-to-3x ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- resources:3.2.0:testResources (default-testResources) @ boot-27-to-3x ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] skip non existing resourceDirectory /Users/sinabastani/Desktop/projects/Java/openrewrite-modernization-lab/apps/boot-27-to-3x/src/test/resources
[INFO]
[INFO] --- compiler:3.10.1:testCompile (default-testCompile) @ boot-27-to-3x ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] <<< rewrite:6.40.0:run (default-cli) < process-test-classes @ boot-27-to-3x <<<
[INFO]
[INFO]
[INFO] --- rewrite:6.40.0:run (default-cli) @ boot-27-to-3x ---
[INFO] Using active recipe(s) [com.example.openrewrite.V1SpringBoot3Migration]
[INFO] Using active styles(s) []
[INFO] Validating active recipes...
[INFO] Project [Boot 2.7 to 3.x Modernization Lab] Resolving Poms...
[INFO] Project [Boot 2.7 to 3.x Modernization Lab] Parsing source files
[INFO] Running recipe(s)...
[WARNING] Changes have been made to apps/boot-27-to-3x/pom.xml by:
[WARNING]     org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_5
[WARNING]         org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_4
[WARNING]             org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_3
[WARNING]                 org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_2
[WARNING]                     org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_1
[WARNING]                         org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_0
[WARNING]                             org.openrewrite.java.spring.boot2.UpgradeSpringBoot_2_7
[WARNING]                                 org.openrewrite.java.spring.boot2.UpgradeSpringBoot_2_6
[WARNING]                                     org.openrewrite.java.spring.boot2.UpgradeSpringBoot_2_5
[WARNING]                                         org.openrewrite.java.spring.boot2.UpgradeSpringBoot_2_4
[WARNING]                                             org.openrewrite.java.spring.boot2.SpringBoot2JUnit4to5Migration
[WARNING]                                                 org.openrewrite.java.testing.junit5.JUnit4to5Migration
[WARNING]                                                     org.openrewrite.java.testing.junit5.ExcludeJUnit4UnlessUsingTestcontainers
[WARNING]                                                         org.openrewrite.maven.ExcludeDependency
[WARNING]                                                     org.openrewrite.java.dependencies.RemoveDependency
[WARNING]                             org.openrewrite.maven.UpgradeParentVersion
[WARNING]                         org.openrewrite.maven.UpgradeParentVersion
[WARNING]                     org.openrewrite.maven.UpgradeParentVersion
[WARNING]                 org.openrewrite.maven.UpgradeParentVersion
[WARNING]             org.openrewrite.maven.UpgradeParentVersion
[WARNING]         org.openrewrite.maven.UpgradeParentVersion: {groupId=org.springframework.boot, artifactId=spring-boot-starter-parent, newVersion=3.5.x}
[WARNING] Changes have been made to apps/boot-27-to-3x/src/main/java/com/example/openrewrite/boot27/CreateUserRequest.java by:
[WARNING]     org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_5
[WARNING]         org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_4
[WARNING]             org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_3
[WARNING]                 org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_2
[WARNING]                     org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_1
[WARNING]                         org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_0
[WARNING]                             org.openrewrite.java.spring.framework.UpgradeSpringFramework_6_0
[WARNING]                                 org.openrewrite.java.migrate.jakarta.JakartaEE10
[WARNING]                                     org.openrewrite.java.migrate.jakarta.JavaxMigrationToJakarta
[WARNING]                                         org.openrewrite.java.migrate.jakarta.JavaxValidationMigrationToJakartaValidation
[WARNING]                                             org.openrewrite.java.ChangePackage
[WARNING] Changes have been made to apps/boot-27-to-3x/src/main/java/com/example/openrewrite/boot27/User.java by:
[WARNING]     org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_5
[WARNING]         org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_4
[WARNING]             org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_3
[WARNING]                 org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_2
[WARNING]                     org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_1
[WARNING]                         org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_0
[WARNING]                             org.openrewrite.java.spring.framework.UpgradeSpringFramework_6_0
[WARNING]                                 org.openrewrite.java.migrate.jakarta.JakartaEE10
[WARNING]                                     org.openrewrite.java.migrate.jakarta.JavaxMigrationToJakarta
[WARNING]                                         org.openrewrite.java.migrate.jakarta.JavaxPersistenceToJakartaPersistence
[WARNING]                                             org.openrewrite.java.ChangePackage
[WARNING] Changes have been made to apps/boot-27-to-3x/src/main/java/com/example/openrewrite/boot27/UserController.java by:
[WARNING]     org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_5
[WARNING]         org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_4
[WARNING]             org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_3
[WARNING]                 org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_2
[WARNING]                     org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_1
[WARNING]                         org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_0
[WARNING]                             org.openrewrite.java.spring.framework.UpgradeSpringFramework_6_0
[WARNING]                                 org.openrewrite.java.migrate.jakarta.JakartaEE10
[WARNING]                                     org.openrewrite.java.migrate.jakarta.JavaxMigrationToJakarta
[WARNING]                                         org.openrewrite.java.migrate.jakarta.JavaxValidationMigrationToJakartaValidation
[WARNING]                                             org.openrewrite.java.ChangePackage
[WARNING] Changes have been made to apps/boot-27-to-3x/src/test/java/com/example/openrewrite/boot27/UserControllerTest.java by:
[WARNING]     org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_5
[WARNING]         org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_4
[WARNING]             org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_3
[WARNING]                 org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_2
[WARNING]                     org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_1
[WARNING]                         org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_0
[WARNING]                             org.openrewrite.java.spring.boot2.UpgradeSpringBoot_2_7
[WARNING]                                 org.openrewrite.java.spring.boot2.UpgradeSpringBoot_2_6
[WARNING]                                     org.openrewrite.java.spring.boot2.UpgradeSpringBoot_2_5
[WARNING]                                         org.openrewrite.java.spring.boot2.UpgradeSpringBoot_2_4
[WARNING]                                             org.openrewrite.java.spring.boot2.SpringBoot2JUnit4to5Migration
[WARNING]                                                 org.openrewrite.java.testing.junit5.JUnit4to5Migration
[WARNING]                                                     org.openrewrite.java.testing.junit5.UpdateTestAnnotation
[WARNING]                                                     org.openrewrite.java.dependencies.RemoveDependency
[WARNING]                                                 org.openrewrite.java.spring.boot2.UnnecessarySpringRunWith
[WARNING]                                                     org.openrewrite.java.testing.junit5.RunnerToExtension
[WARNING]                                                 org.openrewrite.java.spring.boot2.UnnecessarySpringExtension
[WARNING]         org.openrewrite.java.format.AutoFormat
[WARNING] Please review and commit the results.
[WARNING] Estimate time saved: 1h 25m
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  28.349 s
[INFO] Finished at: 2026-06-03T22:54:33+02:00
[INFO] ------------------------------------------------------------------------
```

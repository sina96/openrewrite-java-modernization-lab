# OpenRewrite Basics

OpenRewrite is an automated code transformation tool. It parses source files and build files into syntax trees, runs recipes against those trees, and writes back changed files while preserving most formatting and comments.

In this lab, OpenRewrite is used to demonstrate framework and language migrations that would otherwise require repetitive manual edits.

Official docs worth keeping open while working through this lab:

- [OpenRewrite running recipes](https://docs.openrewrite.org/running-recipes)
- [Quickstart: setting up your project and running recipes](https://docs.openrewrite.org/running-recipes/getting-started)
- [Maven plugin reference](https://docs.openrewrite.org/reference/rewrite-maven-plugin)
- [Recipe catalog](https://docs.openrewrite.org/recipes)
- [Recipe concepts](https://docs.openrewrite.org/concepts-and-explanations/recipes)
- [Declarative YAML format](https://docs.openrewrite.org/reference/yaml-format-reference)

## Core Concepts

### Recipe

A recipe is a named transformation. It can change Java code, Maven POMs, Gradle files, YAML, properties files, XML, and other supported formats.

Examples from this lab:

- `org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_5`
- `org.openrewrite.java.migrate.UpgradeToJava17`
- `org.openrewrite.java.testing.junit5.JUnit4to5Migration`
- `org.openrewrite.java.spring.framework.UpgradeSpringFramework_6_2`

Recipes can be small and specific, such as changing one Maven property, or broad and composite, such as upgrading Spring Boot.

### Composite Recipe

A composite recipe is a recipe made of other recipes. The lab defines custom composite recipes in [../rewrite.yml](../rewrite.yml):

```yaml
type: specs.openrewrite.org/v1beta/recipe
name: com.example.openrewrite.V2Java17Migration
recipeList:
  - org.openrewrite.java.migrate.UpgradeToJava17
  - org.openrewrite.maven.ChangePropertyValue:
      key: maven.compiler.source
      newValue: 17
  - org.openrewrite.maven.ChangePropertyValue:
      key: maven.compiler.target
      newValue: 17
  - org.openrewrite.java.testing.junit5.JUnit4to5Migration
```

This lets the module activate one lab-specific recipe while still using standard OpenRewrite recipes underneath.

### Recipe Catalog

Recipes are loaded from dependencies on the `rewrite-maven-plugin`.

For example:

- `rewrite-spring` provides Spring and Spring Boot recipes.
- `rewrite-migrate-java` provides Java version migration recipes.
- `rewrite-testing-frameworks` provides JUnit and testing framework recipes.

If a recipe cannot be found, the plugin dependency that contains it is probably missing.

## Maven Plugin Wiring

Each module can configure the Rewrite Maven plugin with its own active recipe.

Example:

```xml
<plugin>
  <groupId>org.openrewrite.maven</groupId>
  <artifactId>rewrite-maven-plugin</artifactId>
  <version>6.40.0</version>
  <configuration>
    <configLocation>${maven.multiModuleProjectDirectory}/rewrite.yml</configLocation>
    <activeRecipes>
      <recipe>com.example.openrewrite.V2Java17Migration</recipe>
    </activeRecipes>
  </configuration>
  <dependencies>
    <dependency>
      <groupId>org.openrewrite.recipe</groupId>
      <artifactId>rewrite-migrate-java</artifactId>
      <version>3.34.1</version>
    </dependency>
  </dependencies>
</plugin>
```

The important parts are:

- `configLocation` points to this repo's shared `rewrite.yml`.
- `activeRecipes` chooses which recipe to run for that module.
- Plugin dependencies make recipe catalogs available.

## Dry Run vs Run

Use `dryRun` first:

```bash
mvn -pl apps/java-8-to-17 rewrite:dryRun
```

Dry run does not change source files. It reports what would change and writes a patch file under:

```text
apps/<module>/target/rewrite/rewrite.patch
```

Apply changes with:

```bash
mvn -pl apps/java-8-to-17 rewrite:run
```

After applying recipes, run tests:

```bash
mvn -pl apps/java-8-to-17 test
```

## Reading Output

When Rewrite finds changes, output looks like this:

```text
These recipes would make changes to apps/java-8-to-17/pom.xml:
    org.openrewrite.maven.ChangePropertyValue
    org.openrewrite.java.testing.junit5.JUnit4to5Migration
Patch file available:
    apps/java-8-to-17/target/rewrite/rewrite.patch
```

That means the recipe matched files and produced a diff.

When Rewrite reports this:

```text
Applying recipes would make no changes. No patch file generated.
```

the plugin ran successfully, but no active recipe matched anything it could change. Common causes:

- The recipe is too broad for the sample.
- The code is already migrated.
- The recipe dependency is present, but the selected recipe does not cover that pattern.
- The build file declares versions in a way the recipe does not modify.

## Why Some Work Remains Manual

OpenRewrite is intentionally conservative. It avoids changes where behavior depends on business meaning.

For example, a generic Java 17 migration does not always replace `Date` with `LocalDate`, `Instant`, or `OffsetDateTime`, because each type means something different. In this lab, the Java module used OpenRewrite for the Java/JUnit upgrade, then handled `java.time` cleanup manually.

Good manual follow-up areas:

- Domain-specific date and time choices
- Public API compatibility
- Business logic simplification
- Test naming and coverage
- Runtime behavior that cannot be proven from static code

## Lab Recipes

### V1 Spring Boot 3 Migration

Used by `apps/boot-27-to-3x`.

Automates:

- Spring Boot 2.7 to 3.5
- Jakarta package migration
- Import cleanup
- Formatting

### V2 Java 17 Migration

Used by `apps/java-8-to-17`.

Automates:

- Java build version changes
- JUnit 4 to JUnit Jupiter migration
- Import cleanup
- Formatting

Manual follow-up in this lab:

- Replacing legacy date/time APIs with `java.time`
- Adding parameterized tests
- Improving test names

### V3 Spring Framework 6 Migration

Used by `apps/spring-5-to-6`.

Automates:

- Spring Framework 5.3 to 6.2
- Spring compatibility updates where recipes apply
- Import cleanup
- Formatting

Manual follow-up in larger apps:

- XML config review
- Proxy/lifecycle behavior review
- Test framework modernization
- Production configuration review

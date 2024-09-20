## Introduction

This Gradle plugin provides a comprehensive solution for identifying Gradle subprojects (modules) directly or indirectly affected by code changes. 
By analyzing the differences between a specified base branch and the current active branch, the plugin identifies the modules that require testing, rebuilding, or deployment.

The plugin provides setting to configure base branch used for comparison.

## Setup plugin

The Plugin is available on Gradle Plugin Portal. It can be included in the project by adding the following code in the `settings.gradle(.kts)` file.

```kotlin
pluginManagement {
  repositories {
    gradlePluginPortal()
  }
}
```

And applying the plugin in the `build.gradle(.kts)` file.

```kotlin
plugins {
  id("com.wandera.affected-module-detector") version "<latest_version>"
}
```

Alternatively,  it can be consumed via manual buildscript dependency + plugin application.

Change `build.gradle(.kts)` file to include the following code:

```kotlin
buildscript {

  repositories {
    gradlePluginPortal()
  }
  
  dependencies {
    classpath "com.wandera.affected-module-detector:<latest_version>"
  }
}

apply plugin: "com.wandera.affected-module-detector"
```

## Configuration

The plugin can be configured for various use cases by setting configuration in `build.gradle(.kts)` file.

```kotlin
affectedModuleDetector {
  baseBranch = "<branch_name>"
}
```
**Parameters:**
- `baseBranch` - Base branch to compare with. Default value is `master`.

## Usage

The plugin provides task to determine affected modules.

Task `findAffectedModules` will find and output the list of affected modules into project build directory with name `<project_name>/build/affected-modules-detector/affected-modules.txt`.

Use this shell command to run it:
```shell
./gradlew findAffectedModules
```

plugins {
    kotlin("jvm") version "2.0.20"
    `java-gradle-plugin`
}

group = "com.wandera"
version = "0.1.0"

gradlePlugin {
    website = "https://github.com/wandera/gradle-affected-modules-detector"
    vcsUrl = "https://github.com/wandera/gradle-affected-modules-detector"

    plugins {
        create(name) {
            id = "${group}.${name}"
            displayName = "Affected Modules Detector"
            description = "A plugin to detect modules with code change in a multi-module project"
            implementationClass = "com.wandera.affectedmodulesdetector.AffectedModulesDetectorPlugin"
            tags.set(listOf("module", "android", "detection"))
        }
    }
}

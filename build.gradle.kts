plugins {
    kotlin("jvm") version "2.0.20"
    `java-gradle-plugin`
}

gradlePlugin {
    plugins {
        create(rootProject.name) {
            id = "com.wandera.affectedmodulesdetector"
            implementationClass = "com.wandera.affectedmodulesdetector.AffectedModulesDetectorPlugin"
        }
    }
}

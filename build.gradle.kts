plugins {
    kotlin("jvm") version "2.0.20"
    id("com.gradle.plugin-publish") version "1.2.1"
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

publishing {
    publications {
        create<MavenPublication>("plugin") {
            groupId = "$group"
            artifactId = name
            version = version

            from(components["java"])

            pom {
                name = "Affected Modules Detector"
                description = "A plugin to detect modules with code change in a multi-module project"
                url = "https://github.com/wandera/gradle-affected-modules-detector"
                packaging = "jar"
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id = "petergrajko"
                        name = "Peter Grajko"
                        email = "peter.grajko@jamf.com"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/wandera/gradle-affected-modules-detector.git"
                    developerConnection = "scm:git:ssh://github.com/wandera/gradle-affected-modules-detector.git"
                    url = "https://github.com/wandera/gradle-affected-modules-detector.git"
                }
            }
        }
    }
}

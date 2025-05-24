plugins {
    alias(libs.plugins.spotless)
}

repositories {
    mavenCentral()
}

val included = listOf(
    "core",
    "monolith",
    "domain-core",
    "domain-avro"
)

tasks.register("test") {
    included.forEach { step ->
        dependsOn(gradle.includedBuild(step).task(":test"))
    }
}

tasks.named("build") {
    included.forEach { step ->
        dependsOn(gradle.includedBuild(step).task(":build"))
    }
}

tasks.named("clean") {
    included.forEach { step ->
        dependsOn(gradle.includedBuild(step).task(":clean"))
    }
}

spotless {
    kotlin {
        ktlint()
        leadingTabsToSpaces(4)

        target("**/src/*/kotlin/**/*.kt")
    }

    groovyGradle {
        importOrder()
        removeSemicolons()
        greclipse()
        leadingTabsToSpaces(4)

        target("**/*.gradle")
    }

    yaml {
        jackson()
        leadingTabsToSpaces(2)

        target("config/*.yml", "**/src/**/*.yml")
    }
}
plugins {
    alias(libs.plugins.spotless)
}

repositories {
    mavenCentral()
}

val included =
    listOf(
        "core",
        "monolith",
        "domain-core",
        "domain-avro",
    )

tasks.named("assemble") {
    included.forEach { step ->
        dependsOn(gradle.includedBuild(step).task(":assemble"))
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

tasks.register("test") {
    included.forEach { step ->
        dependsOn(gradle.includedBuild(step).task(":test"))
    }
}

tasks.register("lint") {
    dependsOn("spotlessCheck")
}

spotless {
    kotlin {
        ktlint()
        leadingTabsToSpaces(4)

        target("**/*.kt", "**/*.kts")
    }

    yaml {
        jackson()
        leadingTabsToSpaces(2)

        target("**/*.yml")
        targetExclude(".github/**/*.yml")
    }
}

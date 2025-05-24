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

tasks.register("test") {
    included.forEach { step ->
        dependsOn(gradle.includedBuild(step).task(":test"))
    }
}

tasks.named("assemble") {
    included.forEach { step ->
        dependsOn(gradle.includedBuild(step).task(":assemble"))
    }
}

tasks.named("clean") {
    included.forEach { step ->
        dependsOn(gradle.includedBuild(step).task(":clean"))
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

        target("config/*.yml", "**/src/**/*.yml")
    }
}

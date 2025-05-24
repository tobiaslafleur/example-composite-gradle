plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "se.tobiaslafleur"

repositories {
    mavenCentral()
}

tasks.configureEach {
    outputs.cacheIf { true }
}

plugins {
    alias(libs.plugins.graalvm.buildtools)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

group = "se.tobiaslafleur"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.reflect)
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.web)

    implementation(libs.internal.core.service)

    testImplementation(libs.assertj.core)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

graalvmNative {
    binaries {
        named("main") {
            imageName.set("monolith")
            buildArgs.addAll(
                "--static",
                "--libc=musl"
            )
        }
    }
}

tasks.processTestAot {
    enabled = false
}

tasks.test {
    useJUnitPlatform()

    reports {
        junitXml.outputLocation.set(layout.buildDirectory.dir("test-junit-xml"))
    }

    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.configureEach {
    outputs.cacheIf { true }
}

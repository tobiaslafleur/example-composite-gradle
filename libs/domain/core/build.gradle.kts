plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "se.tobiaslafleur"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.assertj.core)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
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

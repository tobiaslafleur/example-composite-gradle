rootProject.name = "example-composite-gradle"

fun includeLib(
    dir: String,
    suffix: String,
) {
    includeBuild(dir) {
        name = suffix
        dependencySubstitution {
            substitute(module("se.tobiaslafleur.examplecg.lib:$name"))
                .using(project(":"))
        }
    }
}

fun includeService(
    dir: String,
    suffix: String,
) {
    includeBuild(dir) {
        name = suffix
        dependencySubstitution {
            substitute(module("se.tobiaslafleur.examplecg.service:$name"))
                .using(project(":"))
        }
    }
}

includeLib("libs/domain/avro", "domain-avro")
includeLib("libs/domain/core", "domain-core")

includeService("services/core", "core")
includeService("services/monolith", "monolith")

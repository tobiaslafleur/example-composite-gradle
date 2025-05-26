package se.tobiaslafleur.examplecg.service.monolith

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = ["se.tobiaslafleur.examplecg.service"])
class Monolith

fun main(args: Array<String>) {
    SpringApplication(Monolith::class.java).run(*args)
}

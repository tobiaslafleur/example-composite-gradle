package se.tobiaslafleur.examplecg.service.core

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Core

fun main(args: Array<String>) {
    val app = SpringApplication(Core::class.java)
    app.setDefaultProperties(mapOf("spring.config.additional-location" to "file:./config/"))
    app.run(*args)
}

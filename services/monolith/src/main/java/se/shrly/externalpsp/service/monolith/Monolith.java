package se.shrly.externalpsp.service.monolith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class Monolith {

    public static void main(String[] args) {
        var app = new SpringApplication(Monolith.class);

        app.setDefaultProperties(
            Map.of("spring.config.additional-location", "file:./config/")
        );

        app.run(args);
    }
}

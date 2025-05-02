package se.shrly.externalpsp.service.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class Core {

    public static void main(String[] args) {
        var app = new SpringApplication(Core.class);

        app.setDefaultProperties(
            Map.of("spring.config.additional-location", "file:./config/")
        );

        app.run(args);
    }
}

package se.shrly.externalpsp.service.core;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Core {

    public static void main(String[] args) {
        var app = new SpringApplication(Core.class);

        app.setDefaultProperties(Map.of("spring.config.additional-location", "file:./config/"));

        app.run(args);
    }
}

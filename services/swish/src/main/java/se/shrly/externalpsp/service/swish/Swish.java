package se.shrly.externalpsp.service.swish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class Swish {

    public static void main(String[] args) {
        var app = new SpringApplication(Swish.class);

        app.setDefaultProperties(
            Map.of("spring.config.additional-location", "file:./config/")
        );

        app.run(args);
    }
}

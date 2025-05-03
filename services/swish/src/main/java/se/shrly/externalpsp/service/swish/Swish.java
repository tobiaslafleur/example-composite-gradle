package se.shrly.externalpsp.service.swish;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Swish {

    public static void main(String[] args) {
        var app = new SpringApplication(Swish.class);

        app.setDefaultProperties(Map.of("spring.config.additional-location", "file:./config/"));

        app.run(args);
    }
}

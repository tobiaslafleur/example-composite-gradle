package se.shrly.examplecg.service.monolith;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "se.shrly.externalpsp.service")
public class Monolith {

    public static void main(String[] args) {
        var app = new SpringApplication(Monolith.class);

        app.setDefaultProperties(Map.of("spring.config.additional-location", "file:./config/"));

        app.run(args);
    }
}

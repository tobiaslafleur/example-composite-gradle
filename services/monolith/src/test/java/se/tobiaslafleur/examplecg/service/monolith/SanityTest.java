package se.tobiaslafleur.examplecg.service.monolith;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SanityTest {

    @Test
    void sanityCheck() {
        var expected = true;
        assertThat(expected).isTrue();
    }
}

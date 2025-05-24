package se.tobiaslafleur.examplecg.service.monolith

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SanityTest {
    @Test
    fun sanityCheck() {
        val expected = true
        assertThat(expected).isTrue()
    }
}

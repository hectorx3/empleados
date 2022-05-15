package mx.unicaribe.ssav2.integrations.controllers.company

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.StringSpec
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

@MicronautTest
class OtroTest(
    @Client("/") private var client: HttpClient
) : BehaviorSpec({

    /*given("a broomstick") {
        `when`("I sit on it") {
            then("I should be able to fly") {
                val request: HttpRequest<Any> = HttpRequest.GET("/company/meet")
                val body = client.toBlocking().retrieve(request)
                assertNotNull(body)
                assertEquals("Hello World", body)
            }
        }
    }*/
})

@MicronautTest
class DemoTest(
    private val application: EmbeddedApplication<*>
): BehaviorSpec({

    given("at server") {
        then("I should be running") {
            assert(application.isRunning)
        }
    }
})
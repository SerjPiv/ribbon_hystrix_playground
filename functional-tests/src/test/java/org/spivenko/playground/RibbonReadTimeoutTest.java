package org.spivenko.playground;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.time.Duration;

import static io.restassured.RestAssured.given;

@DirtiesContext
public class RibbonReadTimeoutTest extends BaseTestClass {

    private static final Duration RESPONSE_DELAY_500_MILLIS = Duration.ofMillis(500);

    @DynamicPropertySource
    static void loadProperties(DynamicPropertyRegistry registry) {
        registry.add("random-number-service-client.ribbon.listOfServers", () -> "localhost:1001");
        registry.add("random-number-service-client.ribbon.ReadTimeout", () -> 200);
    }

    @Test
    public void shouldFailWithReadTimeOutError() {

        stubForGetRandomNumber(wireMockServerPort1001, 200, RESPONSE_DELAY_500_MILLIS);

        given()
                .when()
                .get("http://localhost:8080/playground/dice/throw")
                .then()
                .assertThat()
                .statusCode(500)
                .body("message", Matchers.containsString("Read timed out executing"));
    }
}

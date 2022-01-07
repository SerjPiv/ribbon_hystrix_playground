package org.spivenko.playground;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.exactly;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.given;

@DirtiesContext
public class RibbonRetriesTest extends BaseTestClass {

    private static final int INITIAL_CALL_PLUS_ONE_RETRY = 2;

    @DynamicPropertySource
    static void loadProperties(DynamicPropertyRegistry registry) {
        registry.add("random-number-service-client.ribbon.listOfServers", () -> "localhost:1000, localhost:1001");
        registry.add("random-number-service-client.ribbon.MaxAutoRetries", () -> 1);
        registry.add("random-number-service-client.ribbon.MaxAutoRetriesNextServer", () -> 1);
        registry.add("random-number-service-client.ribbon.retryableStatusCodes", () -> 500);
    }

    @Test
    public void shouldRetryOneTimeForEachServerIfStatusCode500() {

        stubForGetRandomNumber(wireMockServerPort1000, 500);
        stubForGetRandomNumber(wireMockServerPort1001, 500);

        given()
                .when()
                .get("http://localhost:8080/playground/dice/throw")
                .then()
                .assertThat()
                .statusCode(500);

        wireMockServerPort1000
                .verify(exactly(INITIAL_CALL_PLUS_ONE_RETRY), getRequestedFor(urlEqualTo("/v1/random?min=1&max=6&count=1")));
        wireMockServerPort1001
                .verify(exactly(INITIAL_CALL_PLUS_ONE_RETRY), getRequestedFor(urlEqualTo("/v1/random?min=1&max=6&count=1")));
    }
}

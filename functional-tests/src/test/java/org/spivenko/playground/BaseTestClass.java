package org.spivenko.playground;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = Application.class)
public class BaseTestClass {

    static final WireMockServer wireMockServerPort1000 = new WireMockServer(options().port(1000));
    static final WireMockServer wireMockServerPort1001 = new WireMockServer(options().port(1001));
    static final WireMockServer wireMockServerPort1002 = new WireMockServer(options().port(1002));

    @BeforeAll
    public static void beforeAll() {
        wireMockServerPort1000.resetAll();
        wireMockServerPort1001.resetAll();
        wireMockServerPort1002.resetAll();

        wireMockServerPort1000.start();
        wireMockServerPort1001.start();
        wireMockServerPort1002.start();
    }

    @AfterAll
    public static void afterAll() {
        wireMockServerPort1000.stop();
        wireMockServerPort1001.stop();
        wireMockServerPort1002.stop();
    }

    void stubForGetRandomNumber(WireMockServer wireMockServer, int statusCode, Duration responseDelay) {
        wireMockServer.stubFor(get(urlEqualTo("/v1/random?min=1&max=6&count=1"))
                .willReturn(aResponse().withFixedDelay((int) responseDelay.toMillis())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"result\": 1}")
                        .withStatus(statusCode)));
    }

    void stubForGetRandomNumber(WireMockServer wireMockServer, int statusCode) {
        stubForGetRandomNumber(wireMockServer, statusCode, Duration.ofSeconds(0));
    }
}

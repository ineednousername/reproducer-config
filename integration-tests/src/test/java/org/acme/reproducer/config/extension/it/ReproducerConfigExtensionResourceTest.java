package org.acme.reproducer.config.extension.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ReproducerConfigExtensionResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/reproducer-config-extension")
                .then()
                .statusCode(200)
                .body(is("Hello reproducer-config-extension"));
    }
}

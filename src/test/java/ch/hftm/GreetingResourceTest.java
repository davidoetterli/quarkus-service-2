/**
 * Enthält Tests der REST-Ressourcen.
 */
package ch.hftm;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 * Unit-Test für die GreetingResource.
 */
@QuarkusTest
public class GreetingResourceTest {

    /**
     * Testet den Hello-Endpoint.
     */
    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello from Quarkus REST"));
    }
}

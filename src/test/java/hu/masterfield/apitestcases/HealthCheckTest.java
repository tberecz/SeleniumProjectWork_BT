package hu.masterfield.apitestcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthCheckTest extends BaseAPI_test {
    /* Ez a teszt ellenőzi, hogy az alkalmazás elérhető-e?
     */

    @Test
    public void testHealthCheck() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/health");
        response.prettyPrint();

        response.then()
                .statusCode(200);
                // .body("", equalTo("Application Available"));

        assertEquals(response.prettyPrint(), "Application Available");
    }
}

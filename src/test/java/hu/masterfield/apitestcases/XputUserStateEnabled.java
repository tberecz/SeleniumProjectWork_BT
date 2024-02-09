package hu.masterfield.apitestcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class XputUserStateEnabled extends BaseAPI_test {

int userId = 71;

String requestBody = "{\n" +
        "  \"enabled\": false,\n" +
        "}";

@Test
    public void putUserStateEnabled() {
    given()
            .contentType(ContentType.JSON)
            .header(AUTH_HEADER, "Bearer " + authToken)
            .pathParam("id", userId)
            .body(requestBody)
            .when()
            .put("/api/v1/user/{id}/state/enable")
            .then()
            .statusCode(201);
}
}
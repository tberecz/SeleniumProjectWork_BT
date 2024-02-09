package hu.masterfield.apitestcases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class    Proba extends BaseAPI_test {
    @Test
    public void TC_Proba() {

        /**
        RestAssured.given()
                .header(AUTH_HEADER, "Bearer " + authToken)
                .contentType(JSON)
                .when()
                .get("/api/v1/user")
                .then()
                .statusCode(200);
         */

        ValidatableResponse response = given()
                .header(AUTH_HEADER, "Bearer " + authToken)
                .contentType(JSON)
                .when()
                .get("/api/v1/user")
                .then()
                .statusCode(200)
                .body("username", equalTo("admin@demo.io"));

        // response.prettyPrint();
    }
}
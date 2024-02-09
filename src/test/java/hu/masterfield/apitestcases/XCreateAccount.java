package hu.masterfield.apitestcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class XCreateAccount extends BaseAPI_test {
    @Test
    public void testCreateAccount() {
        String userID = "71";

        String requestBody = "{\n" +
                "  \"accountName\": \"savings\",\n" +
                "  \"accountTypeCode\": \"SAV\",\n" +
                "  \"openingDeposit\": 2000,\n" +
                "  \"ownerTypeCode\": \"IND\"\n" +
                "}";

        given()
                .header(AUTH_HEADER, "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .pathParam("userId", userID)
                .body(requestBody)
                .when()
                .post("/api/v1/user/{userId}/account")
                .then()
                .statusCode(200)
                .body("accountType.id", equalTo(10))
                .body("accountType.code", equalTo("SAV"))
                .body("accountType.category", equalTo("SAV"));
     }
}
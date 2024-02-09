package hu.masterfield.apitestcases;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class XtransferFunds extends BaseAPI_test {
    @Test
    public void testCreateAccount() {
        String userID = "79";

        String requestBody = "{\n" +
                "  \"amount\": 2220,\n" +
                "  \"toAccountId\": 79\n" +
                "}";

        given()
                .header(AUTH_HEADER, "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .pathParam("userId", userID)
                .body(requestBody)
                .when()
                .post("/api/v1/account/{userId}/transfer")
                .then()
                .statusCode(200)
                .body("[0].description", equalTo("Transfer from Account (486130001)"))
                .body("[0].amount", equalTo(2220.0F))
                .body("[0].runningBalance", equalTo(4257.62F));
    }
}
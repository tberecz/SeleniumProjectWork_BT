package hu.masterfield.apitestcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class X1CreateAccount extends BaseAPI_test {

    @Test
    public void testCreateAccount() {
        String userID = "89";
       //  String emailAddress =

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
                .statusCode(201);

        /**

        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .pathParam("userId", userID)
                .when()
                .get("api/v1/user/{userId}");
        response.prettyPrint();
        response.then()
                .statusCode(200)
                .body("userProfile.emailAddress", equalTo(emailAddress));


                .body("accountType.id", equalTo(10))
                .body("accountType.code", equalTo("SAV"))
                .body("accountType.category", equalTo("SAV"));

         */
    }
}
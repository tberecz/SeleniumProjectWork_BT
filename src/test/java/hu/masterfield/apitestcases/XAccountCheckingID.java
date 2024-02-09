package hu.masterfield.apitestcases;

import hu.masterfield.utils.Consts;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class XAccountCheckingID extends BaseAPI_test {

    @Test
    public void testAccountCheckingID() {
        String userID = "71";

        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .pathParam("userId", userID)
                .when()
                .get("/api/v1/user/{userId}/account/checking");

        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("[0].id", equalTo(131))
                .body("[0].name", equalTo("Joint Checking"))
                .body("[0].accountNumber", equalTo(486130005))
                .body("[0].currentBalance", equalTo(772.03f))
                .body("[0].openingBalance", equalTo(2590.00f));
    }
}
package hu.masterfield.apitestcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class XGetAccountID extends BaseAPI_test {

    int userID = 88;
    protected static Logger logger = LogManager.getLogger(XGetAccountID.class);

    @Test
    public void getAccountID() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .pathParam("userId", userID)
                .when()
                .get("/api/v1/account/{userId}");

        logger.trace(response.prettyPrint());

        response.then()
                .statusCode(200)
                .body("id", equalTo(88))
                .body("name", equalTo("Indiviudal Savings"))
                .body("accountNumber", equalTo(486130002))
                .body("currentBalance", equalTo(2979.04f))
                .body("openingBalance", equalTo(1827f))
                .body("interestRate", equalTo(2.02f))
                .body("paymentAmount", equalTo(0f))
                .body("paymentTerm", equalTo(0));

        logger.trace("assertEquals");

        assertEquals(response.path("accountType.id"), Integer.valueOf(11));
        assertEquals(response.path("accountType.code"), "MMA");
        assertEquals(response.path("accountType.category"), "SAV");
        assertEquals(response.path("accountType.name"), "Money Market");
    }
}

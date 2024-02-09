package hu.masterfield.apitestcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class XAccountSavings extends BaseAPI_test {
    protected static Logger logger = LogManager.getLogger(OwnershipTypetest.class);

    @Test
    public void testAccountSavings() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .when()
                .get("/api/v1/account/savings");
        logger.trace(response.prettyPrint());
        response.then()
                .statusCode(200)
                .body("[0].id", is(79))
                .body("[0].name", equalTo("Indiviudal Savings"))
                .body("[0].accountNumber", equalTo(486130001));
    }
}
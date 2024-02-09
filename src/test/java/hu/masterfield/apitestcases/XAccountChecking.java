package hu.masterfield.apitestcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class XAccountChecking extends BaseAPI_test {
    protected static Logger logger = LogManager.getLogger(OwnershipTypetest.class);

    @Test
    public void testAccountCheckings() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .when()
                .get("/api/v1/account/checking");
        logger.trace(response.prettyPrint());
        response.then()
                .statusCode(200)
                .body("[0].id", is(97))
                .body("[0].name", equalTo("Individual Checking"))
                .body("[0].accountNumber", is(486130003))
                .body("[0].currentBalance", equalTo(1049.41F))
                .body("[0].openingBalance", is(2637.00F));
    }
}

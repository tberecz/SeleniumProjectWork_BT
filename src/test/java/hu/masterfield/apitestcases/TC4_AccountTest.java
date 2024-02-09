package hu.masterfield.apitestcases;

import groovy.xml.StreamingDOMBuilder;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC4_AccountTest extends BaseAPI_test {

    GlobalTestData globalTestData = new GlobalTestData();

    @Test
    public void testAccounts() {
        String userID = globalTestData.getProperty(Consts.USER_ID);

        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .pathParams("userId", userID)
                .when()
                .get("/api/v1/user/{userId}/account");

        response.prettyPrint();
        response.then()
                .statusCode(200)
                .body("[0].accountType.code", equalTo("MMA"))
                .body("[0].accountType.category", equalTo("SAV"))
                .body("[0].accountType.name", equalTo("Money Market"))
                .body("[1].accountType.code", equalTo("SAV"))
                .body("[1].accountType.category", equalTo("SAV"))
                .body("[1].accountType.name", equalTo("Savings"))
                .body("[2].accountType.code", equalTo("SCK"))
                .body("[2].accountType.category", equalTo("CHK"))
                .body("[2].accountType.name", equalTo("Standard Checking"));
    }
}
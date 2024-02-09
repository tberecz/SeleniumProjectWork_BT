package hu.masterfield.apitestcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class XgetOwner extends BaseAPI_test {
    protected static Logger logger = LogManager.getLogger(XgetOwner.class);
    String userID = "97";

    @Test
    public void testGetOwner() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .pathParam("userId", userID)
                .when()
                .get("/api/v1/account/{userId}/owner");
        logger.trace(response.prettyPrint());
        response.then()
                .statusCode(200)
                .body("id", equalTo(75))
                .body("username", equalTo("nsmith@masterfield.hu"))
                .body("enabled", equalTo(true))
                .body("accountNonExpired", equalTo(true))
                .body("accountNonLocked", equalTo(true))
                .body("credentialsNonExpired", equalTo(true))
                .body("userProfile.id", equalTo(76))
                .body("userProfile.firstName", equalTo("Nicole"));
    }
}
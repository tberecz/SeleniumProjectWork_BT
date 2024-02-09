package hu.masterfield.apitestcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class XGetUserbyID extends BaseAPI_test {
    protected static Logger logger = LogManager.getLogger(XGetUserbyID.class);
    String userID = "158";

    @Test
    public void testGetUserByID() {
    Response response = given()
            .contentType(ContentType.JSON)
            .header(AUTH_HEADER, "Bearer " + authToken)
            .pathParam("userId", userID)
            .when()
            .get("/api/v1/user/{userId}");

    logger.trace(response.prettyPrint());

    response.prettyPrint();

    response.then()
            .statusCode(200)
            .body("id", equalTo(158))
            .body("username", equalTo("minimalkati@m.com"))
            .body("enabled", equalTo(true))
            .body("accountNonExpired", equalTo(true))
            .body("accountNonLocked", equalTo(true))
            .body("credentialsNonExpired", equalTo(true));
}
}
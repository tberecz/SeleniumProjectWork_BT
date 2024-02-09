package hu.masterfield.apitestcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class XsetUserEnabled extends BaseAPI_test {
    protected static Logger logger = LogManager.getLogger(XsetUserEnabled.class);
    String userID = "158";
    boolean enabled = false;

    @Test
    public void teststeUserEnabledByID() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .pathParam("userId", userID)
                .queryParam("enabled", false)
                .when()
                .put("/api/v1/user/{userId}/state/enable");

        logger.trace(response.prettyPrint());

        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("id", equalTo(158))
                .body("username", equalTo("minimalkati@m.com"))
                .body("enabled", equalTo(false))
                .body("accountNonExpired", equalTo(true))
                .body("accountNonLocked", equalTo(true))
                .body("credentialsNonExpired", equalTo(true));
    }
}
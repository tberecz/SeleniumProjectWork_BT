package hu.masterfield.apitestcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class XupdateUserProfile extends BaseAPI_test {

    protected static Logger logger = LogManager.getLogger(XupdateUserProfile.class);
    String userID = "158";

    String requestBody = "{\n" +
            "  \"address\": \"Baker street 10\",\n" +
            "  \"country\": \"POL\",\n" +
            "  \"firstName\": \"John\",\n" +
            "  \"gender\": \"F\",\n" +
            "  \"homePhone\": \"223344\",\n" +
            "  \"lastName\": \"Doe\",\n" +
            "  \"locality\": \"Boston\",\n" +
            "  \"mobilePhone\": \"332255\",\n" +
            "  \"postalCode\": \"7181\",\n" +
            "  \"region\": \"Maine\",\n" +
            "  \"title\": \"Mrs\",\n" +
            "  \"workPhone\": \"778899\"\n" +
            "}";

    @Test
    public void testUpdateUserProfile() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .pathParam("userId", userID)
                .body(requestBody)
                .when()
                .put("/api/v1/user/{userId}/profile");

        logger.trace(response.prettyPrint());

        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("id", equalTo(159))
                .body("userProfile.address", equalTo("Baker street 10"))
                .body("userProfile.country", equalTo("POL"))
                .body("userProfile.firstName", equalTo("John"))
                .body("userProfile.gender", equalTo('F'))
                .body("userProfile.homePhone", equalTo("223344"))
                .body("userProfile.lastName", equalTo("Doe"))
                .body("userProfile.locality", equalTo("Boston"))
                .body("userProfile.mobilePhone", equalTo("332255"))
                .body("userProfile.postalCode", equalTo("7181"))
                .body("userProfile.region", equalTo("Main"))
                .body("userProfile.title", equalTo("Mrs"))
                .body("userProfile.workPhone", equalTo("778899"));
    }
}
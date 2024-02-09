package hu.masterfield.apitestcases;

import hu.masterfield.datatypes.RegistrationData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TC2_RegistrationAPITest extends BaseAPI_test {
    /**
     * Ez a teszt regisztrál egy profilt a globalTestData properties file-ban
     * megadott adatokkal, és ellenőrzi a profil létrejöttét.
     * POST /api/v1/user
     * createNewUser
     * user-contorller -> /api/v1/user
     */

    protected static Logger logger = LogManager.getLogger(TC2_RegistrationAPITest.class);

    @Test
    public void testCreateNewUser() {
        RegistrationData registrationData = new RegistrationData();

        // A body-ban megadjuk a regisztrációhoz szükséges adatokat.

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("address", registrationData.getAddress());
        requestBody.put("country", registrationData.getCountry());
        requestBody.put("dob", registrationData.getDateOfBirth());
        requestBody.put("emailAddress", registrationData.getEmailAddress());
        requestBody.put("firstName", registrationData.getFirstName());
        requestBody.put("gender", registrationData.getGender());
        requestBody.put("homePhone", registrationData.getHomePhone());
        requestBody.put("lastName", registrationData.getLastName());
        requestBody.put("locality", registrationData.getLocality());
        requestBody.put("mobilePhone", registrationData.getMobilePhone());
        requestBody.put("password", registrationData.getPassword());
        requestBody.put("postalCode", registrationData.getPostalCode());
        requestBody.put("region", registrationData.getRegion());
        requestBody.put("ssn", registrationData.getSocialSecurityNumber());
        requestBody.put("title", registrationData.getTitle());
        requestBody.put("workPhone", registrationData.getWorkPhone());

        logger.info("start POST action");

        // a POST kérés küldése

        given()
                .header(AUTH_HEADER, "Bearer " + authToken)
                .queryParam("role", "USER")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/v1/user")
                .then()
                .statusCode(201);


        // a globalTestData.properties-ből kiolvasott címet ellenőrizzük, amivel regisztráltunk

        String emailAddress = registrationData.getEmailAddress();

        // a regisztrációs adatoknál megadott email címet felhasználva megkeressük a profilt, és kiírjuk a profil adatait

        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .queryParam("username", emailAddress)
                .when()
                .get("api/v1/user/find");
        response.prettyPrint();
        response.then()
                .statusCode(200)
                .body("userProfile.emailAddress", equalTo(emailAddress));

        // Ellenőrizzük, hogy a válaszban helyesek-e a regisztrációs adatok
        assertEquals(registrationData.getFirstName(), response.path("userProfile.firstName"));
        assertEquals(registrationData.getLastName(), response.path("userProfile.lastName"));
        assertEquals(registrationData.getEmailAddress(), response.path("userProfile.emailAddress"));
        assertEquals(registrationData.getSocialSecurityNumber(), response.path("userProfile.ssn"));
        assertEquals(registrationData.getAddress(), response.path("userProfile.address"));
        assertEquals(registrationData.getCountry(), response.path("userProfile.country"));
        assertEquals(registrationData.getDateOfBirth(), response.path("userProfile.dob"));
        assertEquals(registrationData.getGender(), response.path("userProfile.gender"));
        assertEquals(registrationData.getTitle(), response.path("userProfile.title"));
        assertEquals(registrationData.getLocality(), response.path("userProfile.locality"));
        assertEquals(registrationData.getPostalCode(), response.path("userProfile.postalCode"));
        assertEquals(registrationData.getHomePhone(), response.path("userProfile.homePhone"));
        assertEquals(registrationData.getMobilePhone(), response.path("userProfile.mobilePhone"));
        assertEquals(registrationData.getWorkPhone(), response.path("userProfile.workPhone"));
    }
}
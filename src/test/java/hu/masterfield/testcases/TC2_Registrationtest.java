package hu.masterfield.testcases;

import hu.masterfield.datatypes.RegistrationData;
import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.pages.RegistrationFirstPage;
import hu.masterfield.pages.RegistrationSecondPage;
import hu.masterfield.utils.Screenshot;
import jdk.jfr.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TC2 - sikeres regisztráció érvényes adatok megadásával
 */

public class TC2_Registrationtest extends BaseTest {
    private static Logger logger = LogManager.getLogger(TC2_Registrationtest.class);

    @Test
    @DisplayName("TC2_Registration")
    @Description("TC2 . sikeres regisztráció tesztelése érvényes adatokkal")
    @Tag("TC2")
    @Tag("Regisztráció")
    public void TC_Registrationtest(TestInfo testInfo) throws InterruptedException, IOException {
        Thread.sleep(10000);
        logger.info(testInfo + " started.");

        GDPRBannerPage gdprPage = new GDPRBannerPage(driver);

        /* a süti elfogadására szolgáló ablak megjelenésének ellenőrzése */
        assertTrue(gdprPage.isCookieMessageVisible());
        Screenshot.takesScreenshot(driver);
        gdprPage.acceptCookies();
        Screenshot.takesScreenshot(driver);
        logger.info("Login page will be opened...");

        LoginPage loginPageOne = new LoginPage(driver);
        assertTrue(loginPageOne.isLoaded());
        loginPageOne.registrationStart();

        RegistrationData registrationData = new RegistrationData();
        logger.info(registrationData);

        // Regisztráció űrlap 1. oldalának kitöltése
        logger.info("RegistrationFirstPage betöltése");
        RegistrationFirstPage registrationFirstPage = new RegistrationFirstPage(driver);
        assertTrue(registrationFirstPage.isLoaded());
        RegistrationSecondPage registrationSecondPage = registrationFirstPage.registrationFirstPage();

        // Regisztráció űrlap 2. oldalának kitöltése
        logger.info("RegistrationSecondPage betöltése");
        assertTrue(registrationSecondPage.isLoaded());
        LoginPage loginPageTwo = registrationSecondPage.registrationSecondPage();

        // Ellenőrzi, hogy a regisztráció sikeres volt-e, erről megjelent a szöveg.
        logger.info("Regisztráció sikerességének ellenőrzése");
        assertTrue(loginPageTwo.registrationIsSuccesful());

        /*
        if (loginPageTwo.registrationIsSuccesful()) {
            // test passed
            } else {
                fail("Registration failed")
            }
         */ 
    }
}

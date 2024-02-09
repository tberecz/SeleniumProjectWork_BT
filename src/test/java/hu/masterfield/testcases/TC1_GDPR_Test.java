package hu.masterfield.testcases;

import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.utils.Screenshot;
import jdk.jfr.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TC1 - GDPR nyilatkozat sikeres elfogadása
 */
public class TC1_GDPR_Test extends BaseTest {
    protected static Logger logger = LogManager.getLogger(TC1_GDPR_Test.class);

    @Test
    @DisplayName("TC1_GDPR_Test")
    @Description("TC1 - GDPR nyilatkozat sikeres elfogadása")
    @Tag("TC1")
    @Tag("GDPR")
    public void test_TC1_GDPR(TestInfo testInfo) throws IOException, InterruptedException {
        Thread.sleep(10000);
        logger.info(testInfo + " started");
        GDPRBannerPage gdprPage = new GDPRBannerPage(driver);

        /* a süti elfogadására szolgáló ablak megjelenésének ellenőrzése */
        assertTrue(gdprPage.isCookieMessageVisible());
        Screenshot.takesScreenshot(driver);
        gdprPage.acceptCookies();
        Screenshot.takesScreenshot(driver);
        logger.info("Login page will be opened...");

        logger.info("Login");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoaded());
        /*
        A sütik elfogadására szolgáló ablak vizsgálata, hogy az elfogadás után látható még.
         */
        assertFalse(loginPage.isCookieVisible());
        Screenshot.takesScreenshot(driver);

        Thread.sleep(5000);
    }
}
package hu.masterfield.testcases;

import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import jdk.jfr.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class XTC1_Login extends BaseTest {
    protected static Logger logger = LogManager.getLogger(XTC1_Login.class);

    @Test
    @DisplayName("XTC1 - Login")
    @Description("XTC1 - sikeres bejelentkezés regisztrált adatokkal")
    @Tag("XTC1")
    @Tag("Bejelentkezés")

    public void TestCase() throws InterruptedException {
        GlobalTestData globalTestData = new GlobalTestData();
        Thread.sleep(5000);
        logger.info("Logging started.");

        GDPRBannerPage gdprPage = new GDPRBannerPage(driver);
        gdprPage.acceptCookies();

        logger.info("Login page betöltése");
        String emailAddressX = globalTestData.getProperty(Consts.LOGIN_USERNAME);
        String passwordX = globalTestData.getProperty((Consts.LOGIN_PASSWORD));
        String welcomeX = globalTestData.getProperty((Consts.LOGIN_WELCOME));

        logger.info("Login");
        LoginPage loginPageX = new LoginPage(driver);
        assertTrue(loginPageX.isLoaded());

        logger.info("Log into");
        loginPageX.login(emailAddressX, passwordX);

        HomePage homePageX = new HomePage(driver);

        logger.info("Verify welcome message");
        String welcome = homePageX.readWelcome();
        assertEquals(welcomeX, welcome);
        assertEquals(welcomeX, homePageX.readText(homePageX.getWelcomeJosh()));

        logger.info("logout");
        homePageX.logout();
    }
}

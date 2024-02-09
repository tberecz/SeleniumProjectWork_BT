package hu.masterfield.testcases;

import hu.masterfield.pages.CreateData;
import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import hu.masterfield.utils.Screenshot;
import jdk.jfr.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class XTC_Create_Data extends BaseTest {
    protected static Logger logger = LogManager.getLogger(TC3_Login_Test.class);

    @Test
    @DisplayName("XTC1 - Create Data")
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
        String dashboard = "Dashboard";
        String accountChart = "Account Balance Summary";


        logger.info("Login");
        LoginPage loginPageX = new LoginPage(driver);
        assertTrue(loginPageX.isLoaded());

        logger.info("Log into");
        loginPageX.login(emailAddressX, passwordX);

        HomePage homePageX = new HomePage(driver);

        logger.info("Verify welcome message");
        String welcome = homePageX.readWelcome();
        assertEquals(welcomeX, welcome);

        logger.info("Go to Create Data");
        homePageX.createData();

        CreateData createDataX = new CreateData(driver);

        logger.info("Assertation");
        assertEquals(dashboard, createDataX.readPageTitle());

        logger.info("Assertation 2");
        assertEquals(dashboard, createDataX.readText(createDataX.getPagetitle()));

        logger.info("Asseretation 3");
        assertEquals(accountChart, createDataX.readText(createDataX.getAccountChart()));

        logger.info("logout");
        homePageX.logout();
    }
}

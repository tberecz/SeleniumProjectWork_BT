package hu.masterfield.testcases;

import hu.masterfield.pages.*;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class XTC2_PhoneNumbers extends BaseTest {
    protected static Logger logger = LogManager.getLogger(XTC1_Login.class);

    @Test
    public void TestCase() throws InterruptedException {
        GlobalTestData globalTestData = new GlobalTestData();
        Thread.sleep(5000);
        logger.info("Logging started.");

        GDPRBannerPage gdprPage = new GDPRBannerPage(driver);
        gdprPage.acceptCookies();

        logger.info("Login page betöltése");
        String emailAddressX = globalTestData.getProperty(Consts.LOGIN_USERNAME);
        String passwordX = globalTestData.getProperty(Consts.LOGIN_PASSWORD);

        logger.info("Login page is loading");
        LoginPage loginPageX = new LoginPage(driver);
        assertTrue(loginPageX.isLoaded());

        logger.info("Log into");
        loginPageX.login(emailAddressX, passwordX);

        logger.info("Go to the myprofilepage");
        HomePage homePageX = new HomePage(driver);
        homePageX.gotoMyProfilePage();

        logger.info("Textboxok  betöltése");
        MyProfilePage myProfilePageX = new MyProfilePage(driver);
        assertTrue(myProfilePageX.isLoaded());

        logger.info("Értékek összehasonlítása");
        assertEquals(globalTestData.getProperty(Consts.DEF_TITLE), myProfilePageX.readAttribute(myProfilePageX.getTitle()));
        assertEquals(globalTestData.getProperty(Consts.DEF_FIRST_NAME), myProfilePageX.readAttribute(myProfilePageX.getFirstName()));
        assertEquals(globalTestData.getProperty(Consts.DEF_LAST_NAME), myProfilePageX.readAttribute(myProfilePageX.getLastName()));
        assertEquals(globalTestData.getProperty(Consts.DEF_HOMEPHONE), myProfilePageX.readAttribute(myProfilePageX.getHomePhone()));
        assertEquals(globalTestData.getProperty(Consts.DEF_MOBILEPHONE), myProfilePageX.readAttribute(myProfilePageX.getMobilePhone()));
        assertEquals(globalTestData.getProperty(Consts.DEF_WORKPHONE), myProfilePageX.readAttribute(myProfilePageX.getWorkPhone()));
        assertEquals(globalTestData.getProperty(Consts.DEF_ADDRESS), myProfilePageX.readAttribute(myProfilePageX.getAddress()));
        assertEquals(globalTestData.getProperty(Consts.DEF_LOCALITY), myProfilePageX.readAttribute((myProfilePageX.getLocality())));
        assertEquals(globalTestData.getProperty(Consts.DEF_REGION), myProfilePageX.readAttribute(myProfilePageX.getRegion()));
        assertEquals(globalTestData.getProperty(Consts.DEF_POSTAL_CODE), myProfilePageX.readAttribute(myProfilePageX.getPostalCode()));
        assertEquals(globalTestData.getProperty(Consts.DEF_COUNTRY), myProfilePageX.readAttribute(myProfilePageX.getCountry()));


        logger.info("Feliratok összehasonlítása");
        assertEquals(Consts.TITLE, myProfilePageX.readText(myProfilePageX.getTitleName()));
        assertEquals(Consts.FIRST_NAME, myProfilePageX.readText(myProfilePageX.getFirstNameName()));
        assertEquals(Consts.LAST_NAME, myProfilePageX.readText(myProfilePageX.getLastNameName()));
        assertEquals(Consts.HOMEPHONE, myProfilePageX.readText(myProfilePageX.getHomePhoneName()));
        assertEquals(Consts.MOBILEPHONE, myProfilePageX.readText(myProfilePageX.getMobilePhoneName()));
        assertEquals(Consts.WORKPHONE, myProfilePageX.readText(myProfilePageX.getWorkPhoneName()));
        assertEquals(Consts.ADDRESS, myProfilePageX.readText(myProfilePageX.getAddressName()));
        assertEquals(Consts.LOCALITY, myProfilePageX.readText(myProfilePageX.getLocalityName()));
        assertEquals(Consts.REGION, myProfilePageX.readText(myProfilePageX.getRegionName()));
        assertEquals(Consts.POSTAL_CODE, myProfilePageX.readText(myProfilePageX.getPostalCodeName()));
        assertEquals(Consts.COUNTRY, myProfilePageX.readText(myProfilePageX.getCountryName()));

        logger.info("JSON file is creating");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Consts.TITLE, myProfilePageX.readAttribute(myProfilePageX.getTitle()));
        jsonObject.put(Consts.FIRST_NAME, myProfilePageX.readAttribute(myProfilePageX.getFirstName()));
        jsonObject.put(Consts.LAST_NAME, myProfilePageX.readAttribute(myProfilePageX.getLastName()));
        jsonObject.put(Consts.HOMEPHONE, myProfilePageX.readAttribute(myProfilePageX.getHomePhone()));
        jsonObject.put(Consts.MOBILEPHONE, myProfilePageX.readAttribute(myProfilePageX.getMobilePhone()));
        jsonObject.put(Consts.WORKPHONE, myProfilePageX.readAttribute(myProfilePageX.getWorkPhone()));
        jsonObject.put(Consts.ADDRESS, myProfilePageX.readAttribute(myProfilePageX.getAddress()));
        jsonObject.put(Consts.LOCALITY, myProfilePageX.readAttribute((myProfilePageX.getLocality())));
        jsonObject.put(Consts.REGION, myProfilePageX.readAttribute(myProfilePageX.getRegion()));
        jsonObject.put(Consts.POSTAL_CODE, myProfilePageX.readAttribute(myProfilePageX.getPostalCode()));
        jsonObject.put(Consts.COUNTRY, myProfilePageX.readAttribute(myProfilePageX.getCountry()));

        try {
            FileWriter file = new FileWriter("C:\\Users\\tbere\\IdeaProjects\\SeleniumProjectWork\\target\\output.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        logger.info("CSV file is creating");
        String csvFile = "C:\\Users\\tbere\\IdeaProjects\\SeleniumProjectWork\\target\\output.csv";
        try {
            FileWriter fileWriter = new FileWriter(csvFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write header row
            bufferedWriter.write("Label, Value");
            bufferedWriter.newLine();

            // Write data rows
            bufferedWriter.write(Consts.TITLE + ", " + myProfilePageX.readAttribute(myProfilePageX.getTitle()));
            bufferedWriter.newLine();
            bufferedWriter.write(Consts.FIRST_NAME + ", " + myProfilePageX.readAttribute(myProfilePageX.getFirstName()));
            bufferedWriter.newLine();
            bufferedWriter.write(Consts.LAST_NAME + ", " + myProfilePageX.readAttribute(myProfilePageX.getLastName()));
            bufferedWriter.newLine();
            bufferedWriter.write(Consts.HOMEPHONE + ", " + myProfilePageX.readAttribute(myProfilePageX.getHomePhone()));
            bufferedWriter.newLine();
            bufferedWriter.write(Consts.MOBILEPHONE + ", " + myProfilePageX.readAttribute(myProfilePageX.getMobilePhone()));
            bufferedWriter.newLine();
            bufferedWriter.write(Consts.WORKPHONE + ", " + myProfilePageX.readAttribute(myProfilePageX.getWorkPhone()));
            bufferedWriter.newLine();
            bufferedWriter.write(Consts.ADDRESS + ", " + myProfilePageX.readAttribute(myProfilePageX.getAddress()));
            bufferedWriter.newLine();
            bufferedWriter.write(Consts.LOCALITY + ", " + myProfilePageX.readAttribute((myProfilePageX.getLocality())));
            bufferedWriter.newLine();
            bufferedWriter.write(Consts.REGION + ", " + myProfilePageX.readAttribute(myProfilePageX.getRegion()));
            bufferedWriter.newLine();
            bufferedWriter.write(Consts.POSTAL_CODE + ", " + myProfilePageX.readAttribute(myProfilePageX.getPostalCode()));
            bufferedWriter.newLine();
            bufferedWriter.write(Consts.COUNTRY + ", " + myProfilePageX.readAttribute(myProfilePageX.getCountry()));
            bufferedWriter.newLine();

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("logout");
        homePageX.logout();
    }
}
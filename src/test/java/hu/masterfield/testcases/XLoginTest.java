package hu.masterfield.testcases;

import hu.masterfield.browser.WebBrowser;
import hu.masterfield.browser.WebBrowserType;
import hu.masterfield.pages.XLoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XLoginTest {
    private static WebDriver driver;
    private static WebDriverWait wait; // az Ajaxos hívások miatt jó, ha van
    private XLoginPage objLogin;
    private static String baseURL = "http://www.turatars.com";

    @BeforeAll
    public static void setup() {
        driver = WebBrowser.createDriver(WebBrowserType.ChromeSM); // driver példányosítása
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // 1 napnál hosszabb: period, rövidebb: duration
        // driver.manage().window().maximize();
        // wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // wait példányosítása
    }

    @Test
    public void testCase() throws InterruptedException {
        System.out.println(baseURL);
        driver.get(baseURL);
        Thread.sleep(3000);
        objLogin = new XLoginPage(driver);
        objLogin.loginToApp();
        Thread.sleep(3000);
        objLogin.clickCookie();
        Thread.sleep(3000);
        String welcome = objLogin.getWelcome();
        System.out.println(welcome);
        assertEquals("Üdvözlünk tberecz!", welcome);
        objLogin.logout();
    }

    @AfterAll
    public static void cleanup() {
        driver.quit();
    }
}

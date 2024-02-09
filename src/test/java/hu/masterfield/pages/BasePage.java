package hu.masterfield.pages;

import hu.masterfield.utils.GlobalTestData;
import hu.masterfield.utils.Screenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * A Page POM osztályaink innen fogják örökölni az osztályban megvalósított metódusokat.
 * Deklarálja a WebDriver-t, WebDriverWait-et, inicializálja az objektumokat, így ezeket nem kell
 * külön megtennünk minden egyes osztályban.
 */
public class BasePage {
    protected static Logger logger = LogManager.getLogger(BasePage.class);
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static GlobalTestData globalTestData = new GlobalTestData();

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        BasePage.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaded(WebElement webElement) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
        } catch (TimeoutException ex) {
            fail(ex.getMessage());
            return false;
        }
    }

    public boolean isInteractable(WebElement webElement) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(webElement)).isEnabled();
        } catch (TimeoutException ex) {
            fail(ex.getMessage());
            return false;
        }
    }

    public static void takesScreenshot() {
        try {
            Screenshot.takesScreenshot(driver);
        } catch (IOException ex) {
            logger.warn("BasePage.takesScreenshot thrown IOException: " + ex.getMessage() + " , " + ex.getStackTrace());
        }
    }

    public void setTextBox(WebElement webElement, String webElementName, String text) {
        if (webElement.getText().isEmpty()) {
            // to do nothing
        } else {
            try {
                webElement.clear();
                logger.trace(webElementName + ".clear() called");
            } catch (Exception ex) {
                logger.warn("Textbox cannot clear.");
            }
        }
        webElement.sendKeys(text);
        logger.trace(webElementName + ".sendKeys() called");
    }

    public String readText(WebElement webElement) {
        if (!webElement.getText().isEmpty()) {
            logger.trace(webElement + " textbox can be read.");
        } else {
            logger.warn(webElement + "textbox is empty.");
        }
        return webElement.getText();
    }

    public String readAttribute(WebElement webElement) {
        if (!webElement.getAttribute("value").isEmpty()) {
            logger.trace(webElement + " value can be read.");
        } else {
            logger.warn(webElement + "value is empty.");
        }
        return webElement.getAttribute("value");
    }

}
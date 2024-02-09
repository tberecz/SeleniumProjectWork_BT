package hu.masterfield.pages;

// Bejelentkezési képernyő osztálya

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Bejelentkezési képernyő kezelése")
public class LoginPage extends BasePage {
    // minden osztálynak kell egy logger

    protected static Logger logger = LogManager.getLogger(LoginPage.class);
    /*
    Az oldalon található webelementek azonosítása.
    Ezek szükségesek a bejelentkezés megvalósításához és a regisztráció elindításhoz.
     */
    @FindBy(id="username")
    private WebElement usernameInput;

    @FindBy(id="password")
    private WebElement passwordInput;

    @FindBy(id="submit")
    private WebElement submitButton; // sign in gomb

    @FindBy(xpath="//a[text()=' Sign Up Here']") // szándékosan van benne egy szóköz
    private WebElement registrationLink;

    @FindBy(xpath="//span[text()='Registration Successful. Please Login.']") // sikeres regisztráció
    private WebElement registrationSuccesfulLabel;

    @FindBy(id="cc-nb-title")
    // @Findby("//p[text()='Az oldal sütiket használ']")
    private WebElement cookieUsageMessage;

    @FindBy(xpath="//div[span[text()='Success'] and contains(.,'Logout completed.')]")
    private WebElement logoutCompletedMessage; // sikeres bejelentkezés

    @FindBy(id="remember-me")
    private WebElement rememberMeChechbox; //emlékezz rám választó

    public LoginPage(WebDriver driver) { // konstruktor az ősosztály meghívására
        super(driver);
    }

    /*  Ellenőrzi, hogy megjelentek-e az oldalon a megadott elemek.
        @return true az oldal betöltődött, megjelentek az elemek és kattinthatók.
    */
    @Step("Login oldal betöltésének ellenőrzése")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(usernameInput) &&
                isLoaded(passwordInput) &&
                isLoaded(submitButton) &&
                isLoaded(registrationLink) &&
                isLoaded(rememberMeChechbox);
        logger.trace("isLoaded= " + isLoaded);
        return isLoaded;
    }
    /* Ellenőrzi, hogy a sikeres regisztráció után megjelent-e az oldalon a megadott elemm.
        @return true az oldalon megjelent az elvárt szöveg
     */
    @Step("Regisztráció sikerességének ellenőrzése")
    public boolean registrationIsSuccesful() {
        logger.info("registrationIsSuccesful() called");
        boolean isRegistrationSuccesful = isLoaded(registrationSuccesfulLabel);
        logger.trace("registrationIsSucces= " + isRegistrationSuccesful);

        assertEquals("Digital Bank", driver.getTitle());
        assertTrue(driver.getCurrentUrl().endsWith("/bank/register"));
        assertTrue(registrationSuccesfulLabel.getText().contains("Success"));
        assertEquals("Registration Successful. Please Login.", registrationSuccesfulLabel.getText());

        return isRegistrationSuccesful;
    }

    /** Bejelentkezést megvalósító függvény (függvénynek van visszatérési értéke, a metódusnak nincs
     * @param username Digital Bank felhasználó
     * @param password Digital Bank felhasználó jelszava
     * @return a nyitó oldalt megvalósító HomePage objektum
     */

    @Step("Bejelentkezés")
    public HomePage login(String username, String password) {
        logger.info("login() called with username= " + username + ", password= " + " .");

        setTextBox(usernameInput, "usernameInput", username);
        setTextBox(passwordInput, "passwordInput", password);

        takesScreenshot();

        logger.trace("submitButton.click()");
        submitButton.click();

        takesScreenshot();
        return new HomePage(driver);
    }

    @Step("Regisztráció")
    public void registrationStart() {
        logger.trace("registration.click()");
        registrationLink.click();
    }

    // @return true, ha a kijelentkezés sikeres
    @Step("Logout ellenőrzése")
    public boolean validateLogout() {
        boolean islogoutSuccess = isLoaded(usernameInput) &&
                isLoaded(passwordInput) && isLoaded(submitButton) && isLoaded(registrationLink)
                && isLoaded(rememberMeChechbox) && isLoaded(logoutCompletedMessage);
        logger.info("isLogoutSuccess= " + islogoutSuccess);
        return islogoutSuccess;
    }

    /**
     * Cookiek ellenőrzése
     * @return true, ha a "sütiket használ az oldal" szöveget tartalmazó webelement látható az oldalon
     */
    @Step("Cookie-k ellenőrzése")
    public boolean isCookieVisible() {
        boolean isCookieVisible = cookieUsageMessage.isDisplayed();
        logger.info("isCookieVisible= " + isCookieVisible);
        return isCookieVisible;
    }
}

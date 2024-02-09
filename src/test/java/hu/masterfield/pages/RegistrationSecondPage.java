package hu.masterfield.pages;

import hu.masterfield.datatypes.RegistrationData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A regisztrációs űrlap első oldalának osztálya
 */
@Feature("Regisztráció - 2. oldal")
public class RegistrationSecondPage extends BasePage {
    // az oldalon található webelementek azonosítása, amelyekre szükségünk van.

    protected static Logger logger = LogManager.getLogger(RegistrationSecondPage.class);

    @FindBy(id="address")
    private WebElement addressInput;

    @FindBy(id="locality")
    private WebElement localityInput;

    @FindBy(id="region")
    private WebElement regionInput;

    @FindBy(id="postalCode")
    private WebElement postalCodeInput;

    @FindBy(id="country")
    private WebElement countryInput;

    @FindBy(id="homePhone")
    private WebElement homePhoneInput;

    @FindBy(id="mobilePhone")
    private WebElement mobilePhoneInput;

    @FindBy(id="workPhone")
    private WebElement workPhoneInput;

    // feltételek elfogadás
    @FindBy(id="agree-terms")
    private WebElement agreeTermsCheckbox;

    // a regisztráció gombja
    //@Findby(css="button[type='submit']")
    @FindBy(xpath = "//button[@type='submit' and text()='Register']")
    private WebElement registerButton;

    /**
     * Ellenőrzi, hogy megjelentek-e az oldalon a megadott elemek.
     * @return true, ha az oldal betöltődött, megjelentek az elemek és kattinthatóak
     */

    @Step("A regisztrációs űrlap 2. oldalának betöltésének ellenőrzése")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(addressInput) &&
                isLoaded(localityInput) &&
                isLoaded(regionInput) &&
                isLoaded(postalCodeInput) &&
                isLoaded(homePhoneInput) &&
                isLoaded(mobilePhoneInput) &&
                isLoaded(workPhoneInput) &&
                isLoaded(agreeTermsCheckbox) &&
                isLoaded(registerButton);
        logger.trace("isLoaded= " + isLoaded);
        return isLoaded;
    }

    /**
     * Példányosítjuk a RegistrationData osztályt, hogy az oldalon található input mezőket a GlobalTestData.properties
     * file-ban megadott adatokkal tudkjuk kitöltetni.
     * Így a regisztráció 2. oldalának kitöltésekor nem kell felsorolni a sok bemenő paramétert.
     */
    RegistrationData registrationData = new RegistrationData();

    /**
     * A regisztráció második oldalának kitöltését valósítjuk meg.
     */

    @Step("A regisztráció 2. oldalának kitöltése")
    public LoginPage registrationSecondPage() {
        logger.info("registrationSecondpage() called...");
        setTextBox(addressInput, "addressInput", registrationData.getAddress());
        setTextBox(localityInput, "localityInput", registrationData.getLocality());
        setTextBox(regionInput, "regionInput", registrationData.getRegion());
        setTextBox(postalCodeInput, "postalCodeInput", registrationData.getPostalCode());
        setTextBox(countryInput, "countryInput", registrationData.getCountry());
        setTextBox(homePhoneInput, "homePhoneInput", registrationData.getHomePhone());
        setTextBox(mobilePhoneInput, "mobilePhoneInput", registrationData.getMobilePhone());
        setTextBox(workPhoneInput, "workPhoneInput", registrationData.getWorkPhone());

        logger.trace("agreeTermsCheckbox.click() called.");

        if (agreeTermsCheckbox.isSelected()) {
            // to do nothing
        } else {
            agreeTermsCheckbox.click();
        }

        takesScreenshot();

        logger.trace("registerButton.click()");

        registerButton.click();

        return new LoginPage(driver);
    }


    public RegistrationSecondPage(WebDriver driver) {
        super(driver);
    }
}

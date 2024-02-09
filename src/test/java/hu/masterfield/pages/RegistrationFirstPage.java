package hu.masterfield.pages;

import hu.masterfield.datatypes.RegistrationData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * A regisztrációs űrlap első oldalának osztálya
 */
@Feature("Regisztráció - 1. oldal")
public class RegistrationFirstPage extends BasePage {
    // az oldalon található webelementek azonosítása, amelyekre szükségünk van

    // megszólítás megadása
    @FindBy(id = "title")
    private WebElement titleSelect;

    // keresztnév megadása
    @FindBy(id = "firstName")
    private WebElement firstnameInput;

    // vezetéknév megadása
    @FindBy(id = "lastName")
    private WebElement lastnameInput;

    // a férfi nem kiválasztása
    @FindBy(xpath = "//input[@type='radio' and @name='gender' and @value='M']")
    private WebElement genderMaleRadio;

    // a női nem kiválasztása
    @FindBy(css = "input[type='radio'][name='gender'][value='F']")
    private WebElement genderFemaleRadio;

    //születési dátum
    @FindBy(id = "dob")
    private WebElement dateOfBirthInput;

    // tb szám
    @FindBy(id = "ssn")
    private WebElement socialSecurityNumberInput;

    //e-mail cím
    @FindBy(id = "emailAddress")
    private WebElement emailAddressInput;

    // jelszó megadása
    @FindBy(id = "password")
    private WebElement regPasswordInput;

    // jelszó megerősítése
    @FindBy(id = "confirmPassword")
    private WebElement regConfirmPasswordInput;

    // tovább gomb
    @FindBy(xpath = "//button[@type='submit' and text()='Next']")
    WebElement nextPageButton;

    public RegistrationFirstPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Metódus annak ellenőrzésére, hogy betöltődött-e az oldal
     *
     * @return true, ha az oldal betöltödött, megjelentek az elvárt elemek és kattinthatóak
     */
    @Step("A regisztrációs űrlap 1. oldalának betöltésének ellenőrzése")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(titleSelect)
                && isLoaded(firstnameInput)
                && isLoaded(lastnameInput)
                && isLoaded(genderMaleRadio)
                && isLoaded(genderFemaleRadio)
                && isLoaded(dateOfBirthInput)
                && isLoaded(socialSecurityNumberInput)
                && isLoaded(emailAddressInput)
                && isLoaded(regPasswordInput)
                && isLoaded(regPasswordInput)
                && isLoaded(regConfirmPasswordInput)
                && isLoaded(nextPageButton);
        logger.trace("isLoaded= " + isLoaded);
        return isLoaded;
    }

    /**
     * Példányosítjuk a RegistrationData osztályt, hogy az oldalon található input mezőket ki tudjuk
     * tölteni a globalTestData.properties file-ból felolvasott tesztadatokkal.
     */

    RegistrationData registrationData = new RegistrationData();

    /**
     * A regisztráció első oldalát valósítjuk meg.
     */

    @Step("A regisztrációs űrlap 2. oldalának kitöltése")
    public RegistrationSecondPage registrationFirstPage() {
        logger.info("registrationFirstPage() called.");
        logger.trace("titleSelect.select");
        Select selectTitle = new Select(titleSelect);
        selectTitle.selectByVisibleText(registrationData.getTitle());

        setTextBox(firstnameInput, "firstNemInput", registrationData.getFirstName());
        setTextBox(lastnameInput, "lastNameInput", registrationData.getLastName());

        if (registrationData.getGender().equals("M")) {
            if (genderMaleRadio.isSelected()) {
                // to do nothing
            } else {
                logger.trace("genderMaleRadio.click() called");
                genderMaleRadio.click();
            }
        }
        if (registrationData.getGender().equals("F")) {
            if (genderFemaleRadio.isSelected()) {
                // to do nothing
            } else {
                logger.trace("genderFemaleRadio.click() called");
                genderFemaleRadio.click();
            }
        }

        logger.trace("dateOfBirthInput.sendKeys() called.");

        setTextBox(dateOfBirthInput, "dateOfBirthInput", registrationData.getDateOfBirth());

        setTextBox(socialSecurityNumberInput, "socialSecurityInput", registrationData.getSocialSecurityNumber());

        setTextBox(emailAddressInput, "emailAddressInput", registrationData.getEmailAddress());

        setTextBox(regPasswordInput, "regPasswordInput", registrationData.getPassword());

        setTextBox(regConfirmPasswordInput, "regConfirmPasswordInput", registrationData.getConfirmPassword());

        takesScreenshot();

        logger.trace("nextPageButton.click() called.");
        nextPageButton.click();

        return new RegistrationSecondPage(driver);
    }
}
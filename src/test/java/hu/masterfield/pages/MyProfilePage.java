package hu.masterfield.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

@Getter

public class MyProfilePage extends BasePage {
    public MyProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//strong[text()='Title']")
    private WebElement titleName;

    @FindBy(id = "title")
    private WebElement title;

    @FindBy(xpath = "//strong[text()='First Name']")
    private WebElement firstNameName;
    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(xpath = "//strong[text()='Last Name']")
    private WebElement lastNameName;
    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(xpath = "//strong[text()='Home Phone']")
    private WebElement homePhoneName;
    @FindBy(id="homePhone")
    private WebElement homePhone;

    @FindBy(xpath = "//strong[text()='Mobile Phone']")
    private WebElement mobilePhoneName;
    @FindBy(id="mobilePhone")
    private WebElement mobilePhone;

    @FindBy(xpath = "//strong[text()='Work Phone']")
    private WebElement workPhoneName;
    @FindBy(id="workPhone")
    private WebElement workPhone;

    @FindBy(xpath = "//strong[text()='Address']")
    private WebElement addressName;
    @FindBy(id = "address")
    private WebElement address;

    @FindBy(xpath = "//strong[text()='Locality']")
    private WebElement localityName;
    @FindBy(id = "locality")
    private WebElement locality;

    @FindBy(xpath = "//strong[text()='Region']")
    private WebElement regionName;
    @FindBy(id = "region")
    private WebElement region;

    @FindBy(xpath = "//strong[text()='Postal Code']")
    private WebElement postalCodeName;
    @FindBy(id = "postalCode")
    private WebElement postalCode;

    @FindBy(xpath = "//strong[text()='Country']")
    private WebElement countryName;
    @FindBy(id = "country")
    private WebElement country;

    @Step
    public boolean isLoaded() {
        logger.info("Textboxes called");
        boolean isLoaded = isLoaded(homePhone) && isLoaded(mobilePhone)
                && isLoaded(workPhone);
        logger.trace("isLoaded= " + isLoaded);
        return isLoaded;
    }


    public ArrayList<String> textArray(WebElement webElement) {
        ArrayList<String> webElementText = new ArrayList<String>();
            webElementText.add(readText(titleName));

        return webElementText;
    }
}
package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class XLoginPage {

    WebDriver driver;
    private final String username = "tberecz";

    private final String password = "Nikkel75!";

    @FindBy(id="login1")
    private WebElement usernameX;

    @FindBy(id="login2")
    private WebElement passwordX;

    @FindBy(name="act[login]")
    private WebElement submitButtonX;

    @FindBy(xpath = "//p[text()='Beleegyezés']")
    private WebElement cookieAcceptX;

    @FindBy(xpath ="//a[text()='KIJELENTKEZÉS']")
    private WebElement logoutButtonX;

    @FindBy(xpath = "//b[text()='Üdvözlünk tberecz!']")
    private WebElement welcome;

    public XLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUsername(String username) {
            usernameX.sendKeys(username);
        }

    public void setPassword(String password) {
            passwordX.sendKeys(password);
        }

    public void clickLogin() {
            submitButtonX.click();
        }

    public void clickCookie() {
        cookieAcceptX.click();
    }

    public String getWelcome() {
        return welcome.getText();
    }

    public void logout() {
        logoutButtonX.click();
        }

    public void loginToApp(){
        setUsername(username);
        setPassword(password);
        clickLogin();
      }
    }
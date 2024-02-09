package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class XHomePage {
    WebDriver driver;

    public XHomePage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
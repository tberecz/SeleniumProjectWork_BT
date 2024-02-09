package hu.masterfield.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter

public class CreateData extends BasePage{

    public CreateData(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="page-title")
    private WebElement pagetitle;

    @FindBy(xpath = "//h4[@class='mb-3' and text()='Account Balance Summary']")
    private WebElement accountChart;

    @Step
    public String readPageTitle() {
        return pagetitle.getText();
    }
}

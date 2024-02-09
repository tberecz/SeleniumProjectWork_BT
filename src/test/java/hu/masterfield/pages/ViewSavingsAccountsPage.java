package hu.masterfield.pages;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * A Savings típusú accountok megjelenítésének osztálya. Itt történik az adatforrásból létrehozott
 * Saving típusok visszaellenőrzés létrehozás után
 */

@Feature("Megtakarítás típusú account-ok megtekintése / ellenőrzése")
public class ViewSavingsAccountsPage extends BasePage {

    protected static Logger logger = LogManager.getLogger(ViewSavingsAccountsPage.class);

    // AZ oldalon található webelementek azonosítása */
    // accountokhoz tartozó kártyák (amelyek tartalmaznak minden infót az accountról)

    //@FindBy(xpath="//div[@id='firstRow']/div/div/form/div[@class='card-body']")
    //@FindBy(xpath="//div[@id='firstRow']//div[@class='card-body']") -> hosszabb távon jobb, mint a fenti xpath
    //@FindBy(css="div#firstRow div.card-body") ekvivalens az alatta levővel, # az id-t helyettesíti, a . pedig a class-t
    //@FindBy(css="div[id='firstRow'] div[class='card-body']")
    //@FindBy(css="div#firstRow > div > div > form > div.card-body")

    @FindBy(css="div#firstRow div.card-body") // az összes kártya
    private List<WebElement> cards; // beleraktuk mindegyiket ebbe a listába

    // Page title
    @FindBy(xpath = "//h1[text()='View Savings Accounts']")
    private WebElement pageTitle;

    public ViewSavingsAccountsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Megjelenő Saving-ek ellenőrzése
     */

    @Step("Megjelenő Saving-ek ellenőrzése")
    public void validatePage(Saving expectedSaving) {
        logger.info("validatePage() called");
        takesScreenshot();
        WebElement account = findAccount(expectedSaving.getAccountName());
        assertNotNull(account, "Account with accountName= " + expectedSaving.getAccountName()
                + " is not found!");
        WebElement divCartBody = account.findElement(By.xpath("./..")); // az ős lekérése
        // az összes div-et egy listába tesszük
        List<WebElement> cardDivs = divCartBody.findElements(By.cssSelector("div > div")); // az ősön belüli div-ek lekérdezése
        Saving actualSaving = getSavingFromCard(cardDivs);
        assertEquals(expectedSaving, actualSaving);
    }

    /**
     * A felületen található összes Saving kártya átalakítása Java objektummá (Saving)
     * @return Saving objektumokat tartalamzó List-el
     */

    @Step("A felületen levő összes Saving átalakítása Java objektummá")
    public List<Saving> getAllSavings() {
        logger.info("getAllSavings() called.");
        takesScreenshot();
        List<Saving> savingList = new ArrayList<>();
        for (WebElement card : cards) {
            List<WebElement> cardDivs = card.findElements(By.cssSelector("div"));
            savingList.add(getSavingFromCard(cardDivs));
        }
        return savingList;
    }

    private WebElement findAccount(String accountName) {
        // az összes kártyán végig kell menni, minden kártyából ki kell szedni az account nevet
        for (WebElement card : cards) {
        //    String labelAccountName = card.findElement(By.xpath("//div[@class='h4 m-0' and @contenteditable='true']")).getText();
            String labelAccountName = card.findElements(By.tagName("div")).get(0).getText();
            if (labelAccountName.equals(accountName)) {
                return card;
            }
        }
        return null; // ha nem talált egy kártyát sem
    }

    /**
     * Saving objektum létrehozás a kártyából
     * @param cardDivs kártya div elemei
     * @return Saving típusú objektum
     */

    private Saving getSavingFromCard(List<WebElement> cardDivs) { // a kártyák bizonyos elemeit kérjük le ezzel a metódussal
        logger.info("getSavingFromCart() called");

        String openingBalanceText = cardDivs.get(6).getText(); // a 7. elemét kérdezzük le a kártyának, mert lista
        Saving saving = new Saving(cardDivs.get(1).getText().substring(9), // a 2. div-et kérjük le, accountType
                cardDivs.get(2).getText().substring(11), // ownership type lekérdezése
                //carDivs.get(2).getText().replace("Ownership: ....")
                cardDivs.get(0).getText(), // az 1. divet kell lekérni, account name
                openingBalanceText.substring(10, openingBalanceText.lastIndexOf('.'))); // nyitóegyenleg lekérdezése
        return saving;
    }

    @Step("ViewSavingsAccounts oldal betöltésének ellenőrzése")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(pageTitle) && isCardsLoaded(cards);
        logger.info("Page title: " + pageTitle.getText());
        logger.trace("isLoaded= " + isLoaded);
        return isLoaded;
    }

    public boolean isCardsLoaded(List<WebElement> cards) {
        boolean returnValue = false;
        for (WebElement card : cards) {
            if (isLoaded(card)) {
                returnValue = true;
            } else {
                return false;
            }
        }
        return returnValue;
    }
}
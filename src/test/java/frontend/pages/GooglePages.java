package frontend.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.time.Duration;
import java.util.List;


@DefaultUrl("page:googleURL")
public class GooglePages extends PageObject {

    @FindBy(className = "lnXdpd")
    WebElementFacade pageLogo;

    @FindBy(css = "input[class='gLFyf gsfi']")
    WebElementFacade searchBox;

    @FindBy(className = "gNO89b")
    WebElementFacade searchButton;

    String url;
    private final WebDriver driver = super.getDriver();


    public void pageHasLogo() {
        pageLogo.withTimeoutOf(Duration.ofSeconds(3));
        pageLogo.isDisplayed();
    }

    public void enterDataIntoSearchBox(String searchText) {
        searchBox.type(searchText);
    }

    public void clickOnSearchButton() {
        searchButton.click();
    }

    public void rightClickOnTheLink(String linkText) {
        Actions actions = new Actions(driver);
        WebElement partialLinkText = driver.findElement(By.partialLinkText(linkText));
        url = partialLinkText.getAttribute("baseUri");
        actions.contextClick(partialLinkText).perform();
    }

    public void saveTheLinkedURL(String linkText) {
        WebElement partialLinkText = driver.findElement(By.partialLinkText(linkText));
        url = partialLinkText.getAttribute("baseURI");
    }

    //Deprecated
//    public void openInANewTab() {
//        Actions actions = new Actions(driver);
//        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
//        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
//        driver.switchTo().window(tab.get(1));
//    }

    public void openInANewTab() {
        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get(url);
    }

    public void openLinkInANewTab(String linkText) {
        WebElement partialLinkText = driver.findElement(By.partialLinkText(linkText));
        String keys;
        if(SystemUtils.OS_NAME.contains("Mac")) {
            keys = Keys.chord(Keys.COMMAND, Keys.ENTER);
        }
        else {
            keys = Keys.chord(Keys.CONTROL, Keys.ENTER);
        }

        partialLinkText.sendKeys(keys);
        List<String> windows = driver.getWindowHandles().stream().toList();
        driver.switchTo().window(windows.get(1));
    }

    public void clicksOnPartialLinkText(String partialLinkText) {
        WebElement partialLinkTextElement = driver.findElement(By.partialLinkText(partialLinkText));
        partialLinkTextElement.click();
    }

    public void clicksOnLinkText(String linkText) {
        WebElement linkTextElement = driver.findElement(By.partialLinkText(linkText));
        linkTextElement.click();
    }
}

package frontend.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class IMDBPages extends PageObject {

    @FindBy(id = "home_img")
    WebElementFacade pageLogo;

    @FindBy(css = "input[class='ipc-metadata-list-item__label ipc-metadata-list-item__label--link']")
    WebElementFacade allCastAndCrew;

    public void pageHasLogo() {
        pageLogo.withTimeoutOf(Duration.ofSeconds(3));
        pageLogo.isDisplayed();
    }

    public void scrollToText(String elementText) {
        WebElement targetElement = find(By.linkText(elementText));
        ((JavascriptExecutor) super.getDriver()).executeScript("arguments[0].scrollIntoView(true);", targetElement);
    }

    public void clickScrolledElement(String elementText) {
        WebElement targetElement = find(By.linkText(elementText));
        targetElement.click();
    }
}

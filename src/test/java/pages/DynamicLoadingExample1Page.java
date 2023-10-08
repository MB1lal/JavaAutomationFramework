package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;


public class DynamicLoadingExample1Page extends PageObject {

    @FindBy(css = "#start > button")
    private WebElementFacade btnStart;

    private By loadingBar= By.id("loading");

    @FindBy(id = "finish")
    private WebElementFacade txtHello;

    public void clickStart()  {
        btnStart.click();
    }

    private void waitForLoadingBarToFinish() {
        WebDriverWait wait = new WebDriverWait(this.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingBar));
    }

    public String getLoadedElementText() {
        waitForLoadingBarToFinish();
        return txtHello.toString();
    }

}

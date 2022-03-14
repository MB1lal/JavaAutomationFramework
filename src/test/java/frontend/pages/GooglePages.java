package frontend.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

@DefaultUrl("https://www.google.com")
public class GooglePages extends PageObject {
    @FindBy(className = "lnXdpd")
    WebElementFacade pageLogo;

    public void pageHasLogo() {
        pageLogo.withTimeoutOf(Duration.ofSeconds(3));
        pageLogo.isDisplayed();
    }
}

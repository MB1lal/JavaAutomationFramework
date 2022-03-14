package frontend.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://www.google.com")
public class GooglePages extends PageObject {
    @FindBy(id = "test")
    WebElementFacade pageHeader;
}

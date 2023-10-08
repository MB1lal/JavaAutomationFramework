package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class DynamicLoadingPage extends PageObject {
    @FindBy(partialLinkText = "Example 1")
    private WebElementFacade linkEx1;

    @FindBy(partialLinkText = "Example 2")
    private WebElementFacade linkEx2;

    public void navigateToExample(String example) {
        if (example.contains("1")) {
            linkEx1.click();
        }
        if (example.contains("2")) {
            linkEx2.click();
        }
    }
}

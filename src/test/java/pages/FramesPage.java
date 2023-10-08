package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class FramesPage extends PageObject {
    @FindBy(xpath = "//*[text() = 'Nested Frames']")
    private WebElementFacade nestedFramesPage;

    @FindBy(xpath = "//*[text() = 'iFrame']")
    WebElementFacade iFramePage;

    public void navigateToXFramePage(String pageName) {
        switch (pageName) {
            case "Nested Frames":
                nestedFramesPage.click();
                break;
            case "iFrame":
                iFramePage.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid frame page specified");
        }
    }
}

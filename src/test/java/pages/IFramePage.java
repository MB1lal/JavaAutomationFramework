package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class IFramePage extends PageObject {
    @FindBy(id = "mce_0_ifr")
    private WebElementFacade iFrameWithText;

    @FindBy(id = "tinymce")
    private WebElementFacade txtContent;


    public void switchToIFrame() {
       this.getDriver().switchTo().frame(iFrameWithText);
    }

    public void enterTextIntoContent(String inputText) {
        txtContent.clear();
        txtContent.sendKeys(inputText);
    }

    public String getIFrameText() {
        return txtContent.getText().trim();
    }
}

package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Alert;


public class JSAlertPage extends PageObject {
    @FindBy(css = "button[onclick='jsAlert()']")
    private WebElementFacade btnJSAlert;

    @FindBy(css = "button[onclick='jsConfirm()']")
    private WebElementFacade btnJSConfirm;

    @FindBy(css = "button[onclick='jsPrompt()']")
    private WebElementFacade btnJSPrompt;

    @FindBy(id = "result")
    private WebElementFacade lblResult;

    private Alert alert;

    public void triggerJSAlert(String alertType) {
        switch (alertType) {
            case "JS Alert":
                btnJSAlert.click();
                break;
            case "JS Confirm":
                btnJSConfirm.click();
                break;
            case "JS Prompt":
                btnJSPrompt.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid alert type specified");
        }
    }

    public String getResultText() {
        return lblResult.getText();
    }

    public void interactWithAlert(String interaction) {
        alert = this.getDriver().switchTo().alert();
        switch (interaction) {
            case "OK":
                alert.accept();
                break;
            case "CANCEL":
                alert.dismiss();
                break;
            default:
                throw new  IllegalArgumentException("Invalid interaction specified");
        }
    }

    public void enterTextIntoAlert(String inputText) {
        alert = this.getDriver().switchTo().alert();
        alert.sendKeys(inputText);
    }
}
